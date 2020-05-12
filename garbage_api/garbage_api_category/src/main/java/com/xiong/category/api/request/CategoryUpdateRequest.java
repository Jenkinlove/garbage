package com.xiong.category.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = -7382228128598855736L;

    private Long id;

    private String name;

    private String description;

    private String image;

    private Integer cityId;

    private String example;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(id, "category.id.is.null");
    }
}
