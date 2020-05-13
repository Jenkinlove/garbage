package com.xiong.problem.controller;

import com.xiong.common.utils.Assert;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.problem.api.request.ProblemCreateRequest;
import com.xiong.problem.api.request.ProblemDeleteRequest;
import com.xiong.problem.api.request.ProblemPagingRequest;
import com.xiong.problem.api.request.ProblemUpdateRequest;
import com.xiong.problem.api.response.ProblemInfo;
import com.xiong.problem.server.ProblemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    @Autowired
    private ProblemServer problemServer;


    @GetMapping("/paging")
    public PageResult<ProblemInfo> paging(ProblemPagingRequest request) {
        Response<PageResult<ProblemInfo>> response = problemServer.paging(request);
        return Assert.take(response);
    }

    @GetMapping("/single")
    public ProblemInfo findById(@RequestParam("id") Long id) {
        Response<ProblemInfo> response = problemServer.findById(id);
        return Assert.take(response);
    }

    @PostMapping("/create")
    public Long create(ProblemCreateRequest request) {
        Response<Long> response = problemServer.create(request);
        return Assert.take(response);
    }

    @PostMapping("/update")
    public Boolean update(ProblemUpdateRequest request) {
        Response<Boolean> response = problemServer.update(request);
        return Assert.take(response);
    }

    @PostMapping("/delete")
    public Boolean delete(ProblemDeleteRequest request) {
        Response<Boolean> response = problemServer.delete(request);
        return Assert.take(response);
    }


}
