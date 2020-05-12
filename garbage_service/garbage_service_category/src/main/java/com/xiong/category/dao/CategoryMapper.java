package com.xiong.category.dao;

import com.xiong.category.model.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryMapper extends Mapper<Category> {
    List<Category> paging(@Param("cityId") Integer cityId, @Param("name") String name);

    void create(Category category);

    int update(Category category);

    int deleteCategory(@Param("ids") Set<Long> ids);
}
