package com.xiong.article.controller;

import com.xiong.article.api.request.ArticleCreateRequest;
import com.xiong.article.api.request.ArticleDeleteRequest;
import com.xiong.article.api.request.ArticlePagingRequest;
import com.xiong.article.api.request.ArticleUpdateRequest;
import com.xiong.article.api.response.ArticleInfo;
import com.xiong.article.server.ArticleServer;
import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    public ArticleServer articleServer;

    @GetMapping("/paging")
    public PageResult<ArticleInfo> paging(ArticlePagingRequest request) {
        Response<PageResult<ArticleInfo>> response = articleServer.paging(request);
        return Assert.take(response);
    }

    @GetMapping("/single")
    public ArticleInfo findById(@RequestParam("id") Long id) {
        Response<ArticleInfo> response = articleServer.findById(id);
        return Assert.take(response);
    }

    @PostMapping("/create")
    public Long create(ArticleCreateRequest request) {
        Response<Long> response = articleServer.create(request);
        return Assert.take(response);
    }

    @PostMapping("/update")
    public Boolean update(ArticleUpdateRequest request) {
        Response<Boolean> response = articleServer.update(request);
        return Assert.take(response);
    }

    @PostMapping("/delete")
    public Boolean delete(ArticleDeleteRequest request) {
        Response<Boolean> response = articleServer.delete(request);
        return Assert.take(response);
    }

}
