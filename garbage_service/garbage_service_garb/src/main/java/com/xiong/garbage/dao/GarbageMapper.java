package com.xiong.garbage.dao;

import com.xiong.garbage.model.Garbage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Repository
public interface GarbageMapper extends Mapper<Garbage> {

    List<Garbage> paging(HashMap<String, Object> map);

    int update(Garbage garbage);

    int deleteGarbages(@Param("ids") Set<Long> ids);

    List<Garbage> findByName(@Param("name") String name, @Param("cityId") Integer cityId);
}
