package com.xiong.city.service;

import com.xiong.city.api.request.CityCreateRequest;
import com.xiong.city.api.request.CityDeleteRequest;
import com.xiong.city.api.request.CityPagingRequest;
import com.xiong.city.api.request.CityUpdateRequest;
import com.xiong.city.api.response.CityInfo;
import com.xiong.common.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "city-client")
public interface CityFeignClient {

    @GetMapping("/api/city/paging")
    PageResult<CityInfo> paging(@SpringQueryMap CityPagingRequest request);

    @PostMapping("/api/city/create")
    Integer create(@SpringQueryMap CityCreateRequest request);

    @PostMapping("/api/city/update")
    Boolean update(@SpringQueryMap CityUpdateRequest request);

    @GetMapping("/api/city/single")
    CityInfo findById(@RequestParam(value = "id") Integer id);

    @GetMapping("/api/city/check")
    Boolean checkName(@RequestParam(value = "name") String name);

    @PostMapping("/api/city/delete")
    Boolean delete(@SpringQueryMap CityDeleteRequest request);
}
