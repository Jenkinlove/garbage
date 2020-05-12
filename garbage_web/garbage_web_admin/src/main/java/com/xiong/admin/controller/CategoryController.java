package com.xiong.admin.controller;

import com.xiong.category.api.request.CategoryCreateRequest;
import com.xiong.category.api.request.CategoryDeleteRequest;
import com.xiong.category.api.request.CategoryPagingRequest;
import com.xiong.category.api.request.CategoryUpdateRequest;
import com.xiong.category.api.response.CategoryInfo;
import com.xiong.category.service.CategoryFeignClient;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    private CategoryFeignClient categoryFeignClient;

    @GetMapping("/paging")
    public Response<PageResult<CategoryInfo>> paging(CategoryPagingRequest request) {
        try {
            //查询全部
            if (request.getCityId() != null && request.getCityId() == -1) {
                request.setCityId(null);
            }
            PageResult<CategoryInfo> page = categoryFeignClient.paging(request);
            return Response.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.paging.is.fail");
        }
    }

    @PostMapping("/create")
    public Response<Long> create(@RequestBody CategoryCreateRequest request) {
        try {
            Long categoryId = categoryFeignClient.create(request);
            return Response.ok(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.create.is.fail");
        }
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody CategoryUpdateRequest request) {
        try {
            Boolean isOk = categoryFeignClient.update(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.update.is.fail");
        }
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody CategoryDeleteRequest request) {
        try {
            Boolean isOk = categoryFeignClient.delete(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.delete.is.fail");
        }
    }

    @GetMapping("/single")
    public Response<CategoryInfo> findById(Long id) {
        try {
            if (StringUtils.isEmpty(id)) {
                throw new ServiceException("category.id.is.null");
            }
            CategoryInfo categoryInfo = categoryFeignClient.findById(id);
            return Response.ok(categoryInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.find.fail");
        }
    }

}
