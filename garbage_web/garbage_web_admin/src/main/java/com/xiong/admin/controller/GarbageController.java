package com.xiong.admin.controller;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.service.GarbageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
