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
import org.springframework.util.StringUtils;

import java.util.HashMap;
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
            HashMap<String, Object> map = new HashMap<>();
            map.put("cityId", request.getCityId());
            map.put("categoryId", request.getCategoryId());
            if (!StringUtils.isEmpty(request.getName().trim())) {
                map.put("name", request.getName());
            }
            Page<Garbage> page = (Page<Garbage>) garbageMapper.paging(map);
            List<GarbageInfo> garbageInfos = AssembleDataUtils.list2list(page.getResult(), garbageApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), garbageInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.paging.fail");
        }
    }
}
