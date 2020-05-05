package com.xiong.admin.controller;

import com.xiong.garbage.service.GarbageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/garbage")
public class GarbageController {

    @Autowired
    private GarbageFeignClient garbageFeignClient;

}
