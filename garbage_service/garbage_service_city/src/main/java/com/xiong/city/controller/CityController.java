package com.xiong.city.controller;

import com.xiong.city.api.request.CityCreateRequest;
import com.xiong.city.api.request.CityDeleteRequest;
import com.xiong.city.api.request.CityPagingRequest;
import com.xiong.city.api.request.CityUpdateRequest;
import com.xiong.city.api.response.CityInfo;
import com.xiong.city.server.CityServer;
import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityServer cityServer;


    @GetMapping("/paging")
    public PageResult<CityInfo> paging(CityPagingRequest request) {
        Response<PageResult<CityInfo>> response = cityServer.paging(request);
        return Assert.take(response);
    }

    @GetMapping("/all")
    public List<CityInfo> findAll() {
        Response<List<CityInfo>> response = cityServer.findAll();
        return Assert.take(response);
    }

    @PostMapping("/create")
    public Integer create(CityCreateRequest request) {
        Response<Integer> response = cityServer.create(request);
        return Assert.take(response);
    }

    @PostMapping("/update")
    public Boolean update(CityUpdateRequest request) {
        Response<Boolean> response = cityServer.update(request);
        return Assert.take(response);
    }

    @GetMapping("/single")
    public CityInfo findById(@RequestParam(value = "id") Integer id) {
        Response<CityInfo> response = cityServer.findById(id);
        return Assert.take(response);
    }

    @GetMapping("/check")
    public Boolean checkName(@RequestParam(value = "name") String name) {
        Response<Boolean> response = cityServer.checkName(name);
        return Assert.take(response);
    }

    @PostMapping("/delete")
    public Boolean delete(CityDeleteRequest request) {
        Response<Boolean> response = cityServer.delete(request);
        return Assert.take(response);
    }


}
