package com.xiong.category.service;

import com.xiong.category.api.request.CategoryCreateRequest;
import com.xiong.category.api.request.CategoryDeleteRequest;
import com.xiong.category.api.request.CategoryPagingRequest;
import com.xiong.category.api.request.CategoryUpdateRequest;
import com.xiong.category.api.response.CategoryInfo;
import com.xiong.common.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "category-client")
public interface CategoryFeignClient {
    @GetMapping("/api/category/paging")
    PageResult<CategoryInfo> paging(@SpringQueryMap CategoryPagingRequest request);

    @PostMapping("/api/category/create")
    Long create(@SpringQueryMap CategoryCreateRequest request);

    @PostMapping("/api/category/update")
    Boolean update(@SpringQueryMap CategoryUpdateRequest request);

    @GetMapping("/api/category/single")
    CategoryInfo findById(@RequestParam("id") Long id);

    @PostMapping("/api/category/delete")
    Boolean delete(@SpringQueryMap CategoryDeleteRequest request);
}
