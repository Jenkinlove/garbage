package com.xiong.problem.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemDeleteRequest extends AbstractRequest {
    private static final long serialVersionUID = 8190601263982591240L;

    private List<Long> ids;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.notEmpty(ids, "problem.ids.is.empty");
    }
}
