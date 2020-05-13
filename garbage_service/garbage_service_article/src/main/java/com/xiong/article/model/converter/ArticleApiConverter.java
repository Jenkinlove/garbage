package com.xiong.article.model.converter;

import com.xiong.article.api.request.ArticleCreateRequest;
import com.xiong.article.api.request.ArticleUpdateRequest;
import com.xiong.article.api.response.ArticleInfo;
import com.xiong.article.model.Article;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ArticleApiConverter {

    ArticleInfo get(Article article);

    Article get(ArticleCreateRequest request);

    Article get(ArticleUpdateRequest request);

}
