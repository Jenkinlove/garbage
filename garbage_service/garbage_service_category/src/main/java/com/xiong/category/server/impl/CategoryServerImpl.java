package com.xiong.category.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiong.category.api.request.CategoryCreateRequest;
import com.xiong.category.api.request.CategoryDeleteRequest;
import com.xiong.category.api.request.CategoryPagingRequest;
import com.xiong.category.api.request.CategoryUpdateRequest;
import com.xiong.category.api.response.CategoryInfo;
import com.xiong.category.dao.CategoryMapper;
import com.xiong.category.model.Category;
import com.xiong.category.model.converter.CategoryApiConverter;
import com.xiong.category.server.CategoryServer;
import com.xiong.common.utils.AssembleDataUtils;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServerImpl implements CategoryServer {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryApiConverter categoryApiConverter;

    @Override
    public Response<PageResult<CategoryInfo>> paging(CategoryPagingRequest request) {
        try {
            PageHelper.startPage(request.getPage(), request.getPageSize());
            if (!StringUtils.isEmpty(request.getName())) {
                request.setName(request.getName().trim());
            }
            Page<Category> page = (Page<Category>) categoryMapper.paging(request.getCityId(),
                    request.getName());
            List<CategoryInfo> categoryInfos = AssembleDataUtils.list2list(page.getResult(), categoryApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), categoryInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.paging.fail");
        }
    }

    @Override
    public Response<Long> create(CategoryCreateRequest request) {
        try {
            Category category = categoryApiConverter.get(request);
            category.setCreatedAt(new Date());
            categoryMapper.create(category);
            return Response.ok(category.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.create.fail");
        }
    }

    @Override
    public Response<Boolean> update(CategoryUpdateRequest request) {
        try {
            Category category = categoryApiConverter.get(request);
            int updateCount = categoryMapper.update(category);
            return Response.ok(updateCount == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.update.fail");
        }
    }

    @Override
    public Response<Boolean> delete(CategoryDeleteRequest request) {
        try {
            int deleteCount =
                    categoryMapper.deleteCategory(AssembleDataUtils.list2set(request.getIds()));
            return Response.ok(deleteCount != 0);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.delete.fail");
        }
    }

    @Override
    public Response<CategoryInfo> findById(Long id) {
        try {
            Category category = categoryMapper.selectByPrimaryKey(id);
            return Response.ok(categoryApiConverter.get(category));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.find.fail");
        }
    }

}
