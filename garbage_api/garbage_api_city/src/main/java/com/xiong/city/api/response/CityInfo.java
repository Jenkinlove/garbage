package com.xiong.city.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 4486643405588000171L;

    private Integer id;

    private String name;

    private Date createdAt;

}
