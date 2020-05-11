package com.xiong.garbage.dao;

import com.xiong.garbage.model.Garbage;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface GarbageMapper extends Mapper<Garbage> {

    List<Garbage> paging(HashMap<String, Object> map);
}
