package com.xiong.portal.controller;

import com.xiong.common.utils.Response;
import com.xiong.problem.api.request.ProblemFindByRandRequest;
import com.xiong.problem.api.response.ProblemInfo;
import com.xiong.problem.service.ProblemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portal/problem")
public class ProblemController {

    @Autowired
    private ProblemFeignClient problemFeignClient;


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

    @PostMapping("/rand")
    public Response<ProblemInfo> findByRand(@RequestBody ProblemFindByRandRequest request) {
        try {
            ProblemInfo problemInfo = problemFeignClient.findByRand(request);
            return Response.ok(problemInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.find.fail");
        }
    }

}
