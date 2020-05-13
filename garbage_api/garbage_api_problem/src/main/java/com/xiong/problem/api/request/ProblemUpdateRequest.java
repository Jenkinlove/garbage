package com.xiong.problem.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = 8190601263982591240L;

    private Long id;

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
        Assert.nonNull(id, "problem.id.is.null");
    }
}
