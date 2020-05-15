package com.xiong.problem.server;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.problem.api.request.*;
import com.xiong.problem.api.response.ProblemInfo;

public interface ProblemServer {
    Response<PageResult<ProblemInfo>> paging(ProblemPagingRequest request);

    Response<ProblemInfo> findById(Long id);

    Response<Long> create(ProblemCreateRequest request);

    Response<Boolean> update(ProblemUpdateRequest request);

    Response<Boolean> delete(ProblemDeleteRequest request);

    Response<ProblemInfo> findByRand(ProblemFindByRandRequest request);
}
