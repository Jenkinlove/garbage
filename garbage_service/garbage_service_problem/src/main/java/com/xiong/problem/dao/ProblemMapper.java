package com.xiong.problem.dao;

import com.xiong.problem.model.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

@Repository
public interface ProblemMapper extends Mapper<Problem> {
    List<Problem> paging(@Param("name") String name);

    int update(Problem problem);

    int deleteProblem(@Param("ids") Set<Long> ids);

    Problem findByRand(@Param("ids") List<Long> ids);
}
