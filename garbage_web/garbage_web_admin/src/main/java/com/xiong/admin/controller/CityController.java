package com.xiong.admin.controller;

import com.xiong.city.api.request.CityCreateRequest;
import com.xiong.city.api.request.CityDeleteRequest;
import com.xiong.city.api.request.CityPagingRequest;
import com.xiong.city.api.request.CityUpdateRequest;
import com.xiong.city.api.response.CityInfo;
import com.xiong.city.service.CityFeignClient;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/city")
public class CityController {

    @Autowired
    private CityFeignClient cityFeignClient;

    @GetMapping("/paging")
    public Response<PageResult<CityInfo>> paging(CityPagingRequest request) {
        try {
            PageResult<CityInfo> paging = cityFeignClient.paging(request);
            return Response.ok(paging);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.paging.fail");
        }
    }

    @GetMapping("/all")
    public Response<List<CityInfo>> getAllCities() {
        try {
            List<CityInfo> cityInfos = cityFeignClient.findAll();
            return Response.ok(cityInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.find.fail");
        }
    }

    @PostMapping("/create")
    public Response<Integer> create(@RequestBody CityCreateRequest request) {
        try {
            if (StringUtils.isEmpty(request.getName())) {
                throw new ServiceException("city.name.is.null");
            }
            Boolean check = cityFeignClient.checkName(request.getName());
            if (check) {
                return Response.fail("city.name.is.duplicate");
            }
            Integer cityId = cityFeignClient.create(request);
            return Response.ok(cityId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.create.fail");
        }
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody CityUpdateRequest request) {
        try {
            if (StringUtils.isEmpty(request.getName())) {
                throw new ServiceException("city.name.is.null");
            }
            Boolean check = cityFeignClient.checkName(request.getName());
            if (check) {
                return Response.fail("city.name.is.duplicate");
            }
            Boolean isOk = cityFeignClient.update(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.update.fail");
        }
    }

    @GetMapping("/single")
    public Response<CityInfo> findById(Integer id) {
        try {
            if (StringUtils.isEmpty(id)) {
                throw new ServiceException("city.id.is.null");
            }
            CityInfo cityInfo = cityFeignClient.findById(id);
            return Response.ok(cityInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.find.fail");
        }
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody CityDeleteRequest request) {
        try {
            Boolean isOk = cityFeignClient.delete(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.delete.fail");
        }
    }

}
