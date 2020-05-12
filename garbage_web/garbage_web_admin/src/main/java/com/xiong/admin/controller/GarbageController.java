package com.xiong.admin.controller;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbageCreateRequest;
import com.xiong.garbage.api.request.GarbageDeleteRequest;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.request.GarbageUpdateRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.service.GarbageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/garbage")
public class GarbageController {

    @Autowired
    private GarbageFeignClient garbageFeignClient;

    @GetMapping("/paging")
    public Response<PageResult<GarbageInfo>> paging(GarbagePagingRequest request) {
        try {
            PageResult<GarbageInfo> paging = garbageFeignClient.paging(request);
            return Response.ok(paging);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.paging.fail");
        }
    }

    @GetMapping("/single")
    public Response<GarbageInfo> findById(Long id) {
        try {
            GarbageInfo garbageInfo = garbageFeignClient.findById(id);
            return Response.ok(garbageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.find.fail");
        }
    }

    @PostMapping("/create")
    public Response<Long> create(@RequestBody GarbageCreateRequest request) {
        try {
            Long garbageId = garbageFeignClient.create(request);
            return Response.ok(garbageId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.create.is.fail");
        }
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody GarbageUpdateRequest request) {
        try {
            Boolean isOk = garbageFeignClient.update(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.update.is.fail");
        }
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody GarbageDeleteRequest request) {
        try {
            Boolean isOk = garbageFeignClient.delete(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.delete.is.fail");
        }
    }

}
