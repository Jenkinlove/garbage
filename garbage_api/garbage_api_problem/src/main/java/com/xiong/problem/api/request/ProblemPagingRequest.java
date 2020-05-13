package com.xiong.problem.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemPagingRequest extends AbstractRequest {
    private static final long serialVersionUID = -9156690043728292264L;

    private Integer page;

    private Integer pageSize;

    private String name;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(page, "page.is.null");
        Assert.nonNull(pageSize, "page.size.is.null");
    }
}
