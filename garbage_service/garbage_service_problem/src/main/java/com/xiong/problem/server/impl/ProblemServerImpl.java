package com.xiong.problem.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiong.common.utils.AssembleDataUtils;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.problem.api.request.ProblemCreateRequest;
import com.xiong.problem.api.request.ProblemDeleteRequest;
import com.xiong.problem.api.request.ProblemPagingRequest;
import com.xiong.problem.api.request.ProblemUpdateRequest;
import com.xiong.problem.api.response.ProblemInfo;
import com.xiong.problem.dao.ProblemMapper;
import com.xiong.problem.model.Problem;
import com.xiong.problem.model.converter.ProblemApiConverter;
import com.xiong.problem.server.ProblemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProblemServerImpl implements ProblemServer {

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private ProblemApiConverter problemApiConverter;

    @Override
    public Response<PageResult<ProblemInfo>> paging(ProblemPagingRequest request) {
        try {
            PageHelper.startPage(request.getPage(), request.getPageSize());
            Page<Problem> page = (Page<Problem>) problemMapper.paging(request.getName());
            List<ProblemInfo> problemInfos = AssembleDataUtils.list2list(page.getResult(), problemApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), problemInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.paging.fail");
        }
    }

    @Override
    public Response<ProblemInfo> findById(Long id) {
        try {
            Problem problem = problemMapper.selectByPrimaryKey(id);
            return Response.ok(problemApiConverter.get(problem));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.find.fail");
        }
    }

    @Override
    public Response<Long> create(ProblemCreateRequest request) {
        try {
            Problem problem = problemApiConverter.get(request);
            problem.setCreatedAt(new Date());
            problem.setUpdatedAt(new Date());
            problemMapper.insert(problem);
            return Response.ok(problem.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.create.fail");
        }
    }

    @Override
    public Response<Boolean> update(ProblemUpdateRequest request) {
        try {
            Problem problem = problemApiConverter.get(request);
            problem.setUpdatedAt(new Date());
            int updateCount = problemMapper.update(problem);
            return Response.ok(updateCount == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.update.fail");
        }
    }

    @Override
    public Response<Boolean> delete(ProblemDeleteRequest request) {
        try {
            int deleteCount =
                    problemMapper.deleteProblem(AssembleDataUtils.list2set(request.getIds()));
            return Response.ok(deleteCount != 0);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("problem.delete.fail");
        }
    }
}
