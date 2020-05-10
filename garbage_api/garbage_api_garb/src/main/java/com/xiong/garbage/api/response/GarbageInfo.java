package com.xiong.garbage.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GarbageInfo implements Serializable {
    private static final long serialVersionUID = 3609567762368215802L;

    private Long id;

    private String name;

    private Long cityId;

    private Long categoryId;

    private Date createdAt;

    private Date updatedAt;
}
