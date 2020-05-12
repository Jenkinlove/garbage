package com.xiong.garbage.controller;

import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbageCreateRequest;
import com.xiong.garbage.api.request.GarbageDeleteRequest;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.request.GarbageUpdateRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.server.GarbageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/garbage")
public class GarbageController {

    @Autowired
    private GarbageServer garbageServer;


    @GetMapping("/paging")
    public PageResult<GarbageInfo> paging(GarbagePagingRequest request) {
        Response<PageResult<GarbageInfo>> response = garbageServer.paging(request);
        return Assert.take(response);
    }

    @GetMapping("/single")
    public GarbageInfo findById(@RequestParam("id") Long id) {
        Response<GarbageInfo> response = garbageServer.findById(id);
        return Assert.take(response);
    }

    @PostMapping("/create")
    public Long create(GarbageCreateRequest request) {
        Response<Long> response = garbageServer.create(request);
        return Assert.take(response);
    }

    @PostMapping("/update")
    public Boolean update(GarbageUpdateRequest request) {
        Response<Boolean> response = garbageServer.update(request);
        return Assert.take(response);
    }

    @PostMapping("/delete")
    public Boolean delete(GarbageDeleteRequest request) {
        Response<Boolean> response = garbageServer.delete(request);
        return Assert.take(response);
    }

}
