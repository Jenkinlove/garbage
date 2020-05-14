package com.xiong.article.server;

import com.xiong.article.api.request.ArticleCreateRequest;
import com.xiong.article.api.request.ArticleDeleteRequest;
import com.xiong.article.api.request.ArticlePagingRequest;
import com.xiong.article.api.request.ArticleUpdateRequest;
import com.xiong.article.api.response.ArticleInfo;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;

import java.util.List;

public interface ArticleServer {
    Response<PageResult<ArticleInfo>> paging(ArticlePagingRequest request);

    Response<ArticleInfo> findById(Long id);

    Response<Long> create(ArticleCreateRequest request);

    Response<Boolean> update(ArticleUpdateRequest request);

    Response<Boolean> delete(ArticleDeleteRequest request);

    Response<List<ArticleInfo>> findByType(Integer articleType);
}
