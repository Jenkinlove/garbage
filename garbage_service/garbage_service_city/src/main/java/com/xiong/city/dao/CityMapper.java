package com.xiong.city.dao;

import com.xiong.city.model.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

@Repository
public interface CityMapper extends Mapper<City> {

    List<City> paging(@Param("name") String name);

    int update(@Param("id") Integer id, @Param("name") String name);

    City findByName(@Param("name") String name);

    int deleteCities(@Param("ids") Set<Integer> ids);
}
