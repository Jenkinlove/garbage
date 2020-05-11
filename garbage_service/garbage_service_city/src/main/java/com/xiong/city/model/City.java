package com.xiong.city.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = -5462763340413440601L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;

    private Date createdAt;

}
