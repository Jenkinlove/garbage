package com.xiong.article.dao;

import com.xiong.article.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

@Repository
public interface ArticleMapper extends Mapper<Article> {

    List<Article> paging(@Param("name") String name,
                         @Param("articleType") Integer articleType);

    int update(Article article);

    int deleteArticle(@Param("ids") Set<Long> ids);

    List<Article> findByType(@Param("articleType") Integer articleType);
}
