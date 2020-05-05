package com.xiong.garbage.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "garbage")
public class Garbage implements Serializable {
    private static final long serialVersionUID = -5170998795996881519L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String name;

    private Long cityId;

    private Long categoryId;

    private Date createdAt;

    private Date updatedAt;

}
