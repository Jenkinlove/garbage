package com.xiong.problem.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemCreateRequest extends AbstractRequest {
    private static final long serialVersionUID = 8190601263982591240L;

    private String name;

    private String selectA;

    private String selectB;

    private String selectC;

    private String selectD;

    private Integer problemType;

    private String rightAnswer;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(name, "problem.name.is.null");
        Assert.nonNull(selectA, "problem.selectA.is.null");
        Assert.nonNull(selectB, "problem.selectB.is.null");
        Assert.nonNull(selectC, "problem.selectC.is.null");
        Assert.nonNull(selectD, "problem.selectD.is.null");
        Assert.nonNull(problemType, "problem.type.is.null");
        Assert.nonNull(rightAnswer, "problem.right.answer.is.null");
    }
}
