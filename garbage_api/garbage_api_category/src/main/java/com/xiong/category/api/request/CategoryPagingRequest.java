package com.xiong.category.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryPagingRequest extends AbstractRequest {
    private static final long serialVersionUID = 702499885150051819L;

    private Integer page;

    private Integer pageSize;

    private Integer cityId;

    private String name;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(page, "page.is.null");
        Assert.nonNull(pageSize, "page.size.is.null");
    }
}
