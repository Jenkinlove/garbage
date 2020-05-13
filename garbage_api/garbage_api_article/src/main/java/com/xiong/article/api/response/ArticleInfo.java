package com.xiong.article.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleInfo implements Serializable {
    private static final long serialVersionUID = 6313804609069483329L;

    private Long id;

    private String name;

    private String content;

    private Integer articleType;

    private Date createdAt;
}
