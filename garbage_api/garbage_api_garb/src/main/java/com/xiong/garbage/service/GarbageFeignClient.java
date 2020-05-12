package com.xiong.garbage.service;

import com.xiong.common.utils.PageResult;
import com.xiong.garbage.api.request.GarbageCreateRequest;
import com.xiong.garbage.api.request.GarbageDeleteRequest;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.request.GarbageUpdateRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "garbage-client")
public interface GarbageFeignClient {

    @GetMapping("/api/garbage/paging")
    PageResult<GarbageInfo> paging(@SpringQueryMap GarbagePagingRequest request);

    @PostMapping("/api/garbage/create")
    Long create(@SpringQueryMap GarbageCreateRequest request);

    @PostMapping("/api/garbage/update")
    Boolean update(@SpringQueryMap GarbageUpdateRequest request);

    @PostMapping("/api/garbage/delete")
    Boolean delete(@SpringQueryMap GarbageDeleteRequest request);

    @GetMapping("/api/garbage/single")
    GarbageInfo findById(@RequestParam("id") Long id);
}
