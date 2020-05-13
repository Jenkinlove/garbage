package com.xiong.admin.controller;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.problem.api.request.ProblemCreateRequest;
import com.xiong.problem.api.request.ProblemDeleteRequest;
import com.xiong.problem.api.request.ProblemPagingRequest;
import com.xiong.problem.api.request.ProblemUpdateRequest;
import com.xiong.problem.api.response.ProblemInfo;
import com.xiong.problem.service.ProblemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/problem")
public class ProblemController {

    @Autowired
    private ProblemFeignClient problemFeignClient;

    @GetMapping("/paging")
    public Response<PageResult<ProblemInfo>> paging(ProblemPagingRequest request) {
        try {
            PageResult<ProblemInfo> paging = problemFeignClient.paging(request);
            return Response.ok(paging);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.paging.fail");
        }
    }

    @GetMapping("/single")
    public Response<ProblemInfo> findById(@RequestParam("id") Long id) {
        try {
            ProblemInfo problemInfo = problemFeignClient.findById(id);
            return Response.ok(problemInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.find.fail");
        }
    }

    @PostMapping("/create")
    public Response<Long> create(@RequestBody ProblemCreateRequest request) {
        try {
            Long problemId = problemFeignClient.create(request);
            return Response.ok(problemId);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.create.fail");
        }
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody ProblemUpdateRequest request) {
        try {
            Boolean isOk = problemFeignClient.update(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.update.fail");
        }
    }

    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody ProblemDeleteRequest request) {
        try {
            Boolean isOk = problemFeignClient.delete(request);
            return Response.ok(isOk);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.delete.fail");
        }
    }

}
