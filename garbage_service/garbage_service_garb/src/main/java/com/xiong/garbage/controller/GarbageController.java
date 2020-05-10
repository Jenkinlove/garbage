package com.xiong.garbage.controller;

import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.server.GarbageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
