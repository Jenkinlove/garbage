package com.xiong.category.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CategoryInfo implements Serializable {
    private static final long serialVersionUID = 48098069844889997L;

    private Long id;

    private String name;

    private String description;

    private String image;

    private Integer cityId;

    private String example;

    private Date createdAt;
}
