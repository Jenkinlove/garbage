package com.xiong.portal.controller;

import com.xiong.article.api.response.ArticleInfo;
import com.xiong.article.service.ArticleFeignClient;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portal/article")
public class ArticleController {

    @Autowired
    private ArticleFeignClient articleFeignClient;

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

    @GetMapping("/type")
    public Response<List<ArticleInfo>> findByType(@RequestParam("type") Integer articleType) {
        try {
            List<ArticleInfo> articleInfos = articleFeignClient.findByType(articleType);
            return Response.ok(articleInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.find.fail");
        }
    }

}
