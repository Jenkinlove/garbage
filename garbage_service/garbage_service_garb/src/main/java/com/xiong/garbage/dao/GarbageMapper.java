package com.xiong.garbage.dao;

import com.xiong.garbage.model.Garbage;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface GarbageMapper extends Mapper<Garbage> {

}
