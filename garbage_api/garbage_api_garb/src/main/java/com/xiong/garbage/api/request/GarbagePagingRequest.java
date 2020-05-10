package com.xiong.garbage.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GarbagePagingRequest extends AbstractRequest {
    private static final long serialVersionUID = -7505908915472323226L;

    private Integer page;

    private Integer pageSize;

    private String name;

    private Long cityId;

    private Long categoryId;

    @Override
    public void checkParam() {
        super.checkParam();

        Assert.nonNull(page, "page.is.null");
        Assert.nonNull(pageSize, "page.size.is.null");
    }
}
