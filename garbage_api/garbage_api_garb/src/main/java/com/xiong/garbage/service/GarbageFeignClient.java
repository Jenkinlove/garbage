package com.xiong.garbage.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "garbage-client")
public interface GarbageFeignClient {
}
