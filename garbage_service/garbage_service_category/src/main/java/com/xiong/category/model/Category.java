package com.xiong.category.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "category")
public class Category implements Serializable {
    private static final long serialVersionUID = 7960641488646260518L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String name;

    private String description;

    private String image;

    private Integer cityId;

    private String example;

    private Date createdAt;
}
