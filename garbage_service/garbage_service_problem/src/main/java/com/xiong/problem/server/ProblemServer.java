package com.xiong.problem.server;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.problem.api.request.ProblemCreateRequest;
import com.xiong.problem.api.request.ProblemDeleteRequest;
import com.xiong.problem.api.request.ProblemPagingRequest;
import com.xiong.problem.api.request.ProblemUpdateRequest;
import com.xiong.problem.api.response.ProblemInfo;

public interface ProblemServer {
    Response<PageResult<ProblemInfo>> paging(ProblemPagingRequest request);

    Response<ProblemInfo> findById(Long id);

    Response<Long> create(ProblemCreateRequest request);

    Response<Boolean> update(ProblemUpdateRequest request);

    Response<Boolean> delete(ProblemDeleteRequest request);
}
