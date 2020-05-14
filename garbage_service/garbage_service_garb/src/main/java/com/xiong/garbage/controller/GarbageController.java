package com.xiong.garbage.controller;

import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.*;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.server.GarbageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public List<GarbageInfo> findByName(GarbageFindByNameRequest request) {
        Response<List<GarbageInfo>> response = garbageServer.findByName(request);
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
