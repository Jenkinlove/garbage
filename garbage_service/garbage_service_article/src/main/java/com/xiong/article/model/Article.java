package com.xiong.article.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = 4250619616142732347L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String name;

    private String content;

    private Integer articleType;

    private Date createdAt;
}
