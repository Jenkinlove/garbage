package com.xiong.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -5822103001313992411L;

    private Long total;//总记录数
    private List<T> rows;//数据


    public static <T> PageResult<T> paging(Long total, List<T> rows) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setRows(rows);
        pageResult.setTotal(total);
        return pageResult;
    }
}
