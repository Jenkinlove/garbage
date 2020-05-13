package com.xiong.problem.service;

import com.xiong.common.utils.PageResult;
import com.xiong.problem.api.request.ProblemCreateRequest;
import com.xiong.problem.api.request.ProblemDeleteRequest;
import com.xiong.problem.api.request.ProblemPagingRequest;
import com.xiong.problem.api.request.ProblemUpdateRequest;
import com.xiong.problem.api.response.ProblemInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "problem-client")
public interface ProblemFeignClient {

    @GetMapping("/api/problem/paging")
    public PageResult<ProblemInfo> paging(@SpringQueryMap ProblemPagingRequest request);

    @GetMapping("/api/problem/single")
    public ProblemInfo findById(@RequestParam("id") Long id);

    @PostMapping("/api/problem/create")
    public Long create(@SpringQueryMap ProblemCreateRequest request);

    @PostMapping("/api/problem/update")
    public Boolean update(@SpringQueryMap ProblemUpdateRequest request);

    @PostMapping("/api/problem/delete")
    public Boolean delete(@SpringQueryMap ProblemDeleteRequest request);

}
