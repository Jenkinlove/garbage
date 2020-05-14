package com.xiong.article.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiong.article.api.request.ArticleCreateRequest;
import com.xiong.article.api.request.ArticleDeleteRequest;
import com.xiong.article.api.request.ArticlePagingRequest;
import com.xiong.article.api.request.ArticleUpdateRequest;
import com.xiong.article.api.response.ArticleInfo;
import com.xiong.article.dao.ArticleMapper;
import com.xiong.article.model.Article;
import com.xiong.article.model.converter.ArticleApiConverter;
import com.xiong.article.server.ArticleServer;
import com.xiong.common.utils.AssembleDataUtils;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServerImpl implements ArticleServer {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleApiConverter articleApiConverter;


    @Override
    public Response<PageResult<ArticleInfo>> paging(ArticlePagingRequest request) {
        try {
            PageHelper.startPage(request.getPage(), request.getPageSize());
            Page<Article> page = (Page<Article>) articleMapper.paging(request.getName(),
                    request.getArticleType());
            List<ArticleInfo> articleInfos = AssembleDataUtils.list2list(page.getResult(),
                    articleApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), articleInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.paging.fail");
        }
    }

    @Override
    public Response<ArticleInfo> findById(Long id) {
        try {
            Article article = articleMapper.selectByPrimaryKey(id);
            return Response.ok(articleApiConverter.get(article));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.find.fail");
        }
    }

    @Override
    public Response<List<ArticleInfo>> findByType(Integer articleType) {
        try {
            List<Article> articles = articleMapper.findByType(articleType);
            List<ArticleInfo> articleInfos = AssembleDataUtils.list2list(articles, articleApiConverter::get);
            return Response.ok(articleInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.find.fail");
        }
    }

    @Override
    public Response<Long> create(ArticleCreateRequest request) {
        try {
            Article article = articleApiConverter.get(request);
            article.setCreatedAt(new Date());
            articleMapper.insert(article);
            return Response.ok(article.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.create.fail");
        }
    }

    @Override
    public Response<Boolean> update(ArticleUpdateRequest request) {
        try {
            Article article = articleApiConverter.get(request);
            int updateCount = articleMapper.update(article);
            return Response.ok(updateCount == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.update.fail");
        }
    }

    @Override
    public Response<Boolean> delete(ArticleDeleteRequest request) {
        try {
            int deleteCount =
                    articleMapper.deleteArticle(AssembleDataUtils.list2set(request.getIds()));
            return Response.ok(deleteCount != 0);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("article.delete.fail");
        }
    }
}
