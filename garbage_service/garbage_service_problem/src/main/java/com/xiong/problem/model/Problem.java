package com.xiong.problem.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "problem")
public class Problem implements Serializable {
    private static final long serialVersionUID = 2040305075974617666L;

    @Id
    @KeySql(useGeneratedKeys = true)
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
