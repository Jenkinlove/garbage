package com.xiong.category.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryCreateRequest extends AbstractRequest {
    private static final long serialVersionUID = 3883825467132839807L;

    private String name;

    private String description;

    private String image;

    private Integer cityId;

    private String example;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(name, "category.name.is.null");
        Assert.nonNull(cityId, "category.city.is.null");
    }
}
