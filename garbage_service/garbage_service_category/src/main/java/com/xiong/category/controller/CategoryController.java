package com.xiong.category.controller;

import com.xiong.category.api.request.CategoryCreateRequest;
import com.xiong.category.api.request.CategoryDeleteRequest;
import com.xiong.category.api.request.CategoryPagingRequest;
import com.xiong.category.api.request.CategoryUpdateRequest;
import com.xiong.category.api.response.CategoryInfo;
import com.xiong.category.server.CategoryServer;
import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryServer categoryServer;


    @GetMapping("/paging")
    public PageResult<CategoryInfo> paging(CategoryPagingRequest request) {
        Response<PageResult<CategoryInfo>> response = categoryServer.paging(request);
        return Assert.take(response);
    }

    @PostMapping("/create")
    public Long create(CategoryCreateRequest request) {
        Response<Long> response = categoryServer.create(request);
        return Assert.take(response);
    }

    @PostMapping("/update")
    public Boolean update(CategoryUpdateRequest request) {
        Response<Boolean> response = categoryServer.update(request);
        return Assert.take(response);
    }

    @PostMapping("/delete")
    public Boolean delete(CategoryDeleteRequest request) {
        Response<Boolean> response = categoryServer.delete(request);
        return Assert.take(response);
    }

    @GetMapping("/single")
    public CategoryInfo findById(@RequestParam("id") Long id) {
        Response<CategoryInfo> response = categoryServer.findById(id);
        return Assert.take(response);
    }

}
