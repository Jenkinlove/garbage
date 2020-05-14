package com.xiong.garbage.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiong.common.utils.AssembleDataUtils;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.*;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.dao.GarbageMapper;
import com.xiong.garbage.model.Garbage;
import com.xiong.garbage.model.converter.GarbageApiConverter;
import com.xiong.garbage.server.GarbageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
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
            if (!StringUtils.isEmpty(request.getName())) {
                map.put("name", request.getName().trim());
            }
            Page<Garbage> page = (Page<Garbage>) garbageMapper.paging(map);
            List<GarbageInfo> garbageInfos = AssembleDataUtils.list2list(page.getResult(), garbageApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), garbageInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.paging.fail");
        }
    }

    @Override
    public Response<Long> create(GarbageCreateRequest request) {
        try {
            Garbage garbage = garbageApiConverter.get(request);
            garbage.setCreatedAt(new Date());
            garbage.setUpdatedAt(new Date());
            garbageMapper.insert(garbage);
            return Response.ok(garbage.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.create.fail");
        }
    }

    @Override
    public Response<Boolean> update(GarbageUpdateRequest request) {
        try {
            Garbage garbage = garbageApiConverter.get(request);
            garbage.setUpdatedAt(new Date());
            int updateCount = garbageMapper.update(garbage);
            return Response.ok(updateCount == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.update.fail");
        }
    }

    @Override
    public Response<Boolean> delete(GarbageDeleteRequest request) {
        try {
            int deleteCount =
                    garbageMapper.deleteGarbages(AssembleDataUtils.list2set(request.getIds()));
            return Response.ok(deleteCount != 0);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.delete.fail");
        }
    }

    @Override
    public Response<GarbageInfo> findById(Long id) {
        try {
            Garbage garbage = garbageMapper.selectByPrimaryKey(id);
            return Response.ok(garbageApiConverter.get(garbage));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.find.fail");
        }
    }

    @Override
    public Response<List<GarbageInfo>> findByName(GarbageFindByNameRequest request) {
        try {
            if (!StringUtils.isEmpty(request.getName())) {
                request.setName(request.getName().trim());
            }
            List<Garbage> garbageList = garbageMapper.findByName(request.getName(),
                    request.getCityId());
            List<GarbageInfo> garbageInfos = AssembleDataUtils.list2list(garbageList,
                    garbageApiConverter::get);
            return Response.ok(garbageInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.find.fail");
        }
    }

}
