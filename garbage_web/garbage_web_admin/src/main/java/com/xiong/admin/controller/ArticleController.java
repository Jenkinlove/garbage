package com.xiong.admin.controller;

import com.xiong.article.api.request.ArticleCreateRequest;
import com.xiong.article.api.request.ArticleDeleteRequest;
import com.xiong.article.api.request.ArticlePagingRequest;
import com.xiong.article.api.request.ArticleUpdateRequest;
import com.xiong.article.api.response.ArticleInfo;
import com.xiong.article.service.ArticleFeignClient;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/article")
public class ArticleController {

    @Autowired
    private ArticleFeignClient articleFeignClient;

    @GetMapping("/paging")
    public Response<PageResult<ArticleInfo>> paging(ArticlePagingRequest request) {
        try {
            if (request.getArticleType() == -1) {
                request.setArticleType(null);
            }
            PageResult<ArticleInfo> paging = articleFeignClient.paging(request);
            return Response.ok(paging);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.paging.fail");
        }
    }

    @GetMapping("/single")
    public Response<ArticleInfo> findById(@RequestParam("id") Long id) {
        try {
            ArticleInfo articleInfo = articleFeignClient.findById(id);
            return Response.ok(articleInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.find.fail");
        }
    }

    @PostMapping("/create")
    public Response<Long> create(@RequestBody ArticleCreateRequest request) {
        try {
            Long problemId = articleFeignClient.create(request);
            return Response.ok(problemId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.create.fail");
        }
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody ArticleUpdateRequest request) {
        try {
            Boolean isOk = articleFeignClient.update(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.update.fail");
        }
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody ArticleDeleteRequest request) {
        try {
            Boolean isOk = articleFeignClient.delete(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.delete.fail");
        }
    }

}
