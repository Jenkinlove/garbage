package com.xiong.problem.model.converter;

import com.xiong.problem.api.request.ProblemCreateRequest;
import com.xiong.problem.api.request.ProblemUpdateRequest;
import com.xiong.problem.api.response.ProblemInfo;
import com.xiong.problem.model.Problem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProblemApiConverter {

    ProblemInfo get(Problem problem);

    Problem get(ProblemCreateRequest request);

    Problem get(ProblemUpdateRequest request);

}
