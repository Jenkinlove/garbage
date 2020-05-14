package com.xiong.portal.controller;

import com.xiong.city.api.response.CityInfo;
import com.xiong.city.service.CityFeignClient;
import com.xiong.common.utils.Response;
import com.xiong.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portal/city")
public class CityController {

    @Autowired
    private CityFeignClient cityFeignClient;

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

}
