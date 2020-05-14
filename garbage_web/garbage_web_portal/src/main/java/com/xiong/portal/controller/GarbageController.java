package com.xiong.portal.controller;

import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbageFindByNameRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.service.GarbageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portal/garbage")
public class GarbageController {

    @Autowired
    private GarbageFeignClient garbageFeignClient;


    @GetMapping("/search")
    public Response<List<GarbageInfo>> findById(GarbageFindByNameRequest request) {
        try {
            List<GarbageInfo> garbageInfos = garbageFeignClient.findByName(request);
            return Response.ok(garbageInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("garbage.find.fail");
        }
    }

}
