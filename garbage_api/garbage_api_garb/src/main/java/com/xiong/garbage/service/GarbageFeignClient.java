package com.xiong.garbage.service;

import com.xiong.common.utils.PageResult;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "garbage-client")
public interface GarbageFeignClient {

    @GetMapping("/api/garbage/paging")
    PageResult<GarbageInfo> paging(@SpringQueryMap GarbagePagingRequest request);
}
