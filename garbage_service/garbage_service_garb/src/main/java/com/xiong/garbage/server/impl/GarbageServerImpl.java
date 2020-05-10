package com.xiong.garbage.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiong.common.utils.AssembleDataUtils;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.dao.GarbageMapper;
import com.xiong.garbage.model.Garbage;
import com.xiong.garbage.model.converter.GarbageApiConverter;
import com.xiong.garbage.server.GarbageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class GarbageServerImpl implements GarbageServer {

    @Autowired
    private GarbageMapper garbageMapper;
    @Autowired
    private GarbageApiConverter garbageApiConverter;

    @Override
    public Response<PageResult<GarbageInfo>> paging(GarbagePagingRequest request) {
        try {
            PageHelper.startPage(request.getPage(), request.getPageSize());
            Example example = new Example(Garbage.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("cityId", request.getCityId());
            criteria.andEqualTo("categoryId", request.getCategoryId());
            Page<Garbage> page = (Page<Garbage>) garbageMapper.selectByExample(example);
            List<GarbageInfo> garbageInfos = AssembleDataUtils.list2list(page.getResult(), garbageApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), garbageInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.paging.fail");
        }
    }
}
