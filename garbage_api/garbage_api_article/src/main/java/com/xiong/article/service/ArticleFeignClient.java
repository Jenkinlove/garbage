package com.xiong.article.service;

import com.xiong.article.api.request.ArticleCreateRequest;
import com.xiong.article.api.request.ArticleDeleteRequest;
import com.xiong.article.api.request.ArticlePagingRequest;
import com.xiong.article.api.request.ArticleUpdateRequest;
import com.xiong.article.api.response.ArticleInfo;
import com.xiong.common.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "article-client")
@RequestMapping("/api/article")
public interface ArticleFeignClient {
    @GetMapping("/paging")
    public PageResult<ArticleInfo> paging(@SpringQueryMap ArticlePagingRequest request);

    @GetMapping("/single")
    public ArticleInfo findById(@RequestParam("id") Long id);

    @PostMapping("/create")
    public Long create(@SpringQueryMap ArticleCreateRequest request);

    @PostMapping("/update")
    public Boolean update(@SpringQueryMap ArticleUpdateRequest request);

    @PostMapping("/delete")
    public Boolean delete(@SpringQueryMap ArticleDeleteRequest request);

    @GetMapping("/get")
    public List<ArticleInfo> findByType(@RequestParam("articleType") Integer articleType);
}
