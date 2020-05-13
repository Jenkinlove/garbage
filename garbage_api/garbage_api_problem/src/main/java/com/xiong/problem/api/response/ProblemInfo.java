package com.xiong.problem.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProblemInfo implements Serializable {
    private static final long serialVersionUID = 4972445062887474188L;

    private Long id;

    private String name;

    private String selectA;

    private String selectB;

    private String selectC;

    private String selectD;

    private Integer problemType;

    private String rightAnswer;

    private Date createdAt;

    private Date updatedAt;


}
