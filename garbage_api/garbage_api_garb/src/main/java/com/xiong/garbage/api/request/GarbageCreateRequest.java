package com.xiong.garbage.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GarbageCreateRequest extends AbstractRequest {
    private static final long serialVersionUID = -2720713734759897657L;

    private String name;

    private Long cityId;

    private Long categoryId;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(name, "garbage.name.is.null");
        Assert.nonNull(cityId, "garbage.city.id.is.null");
        Assert.nonNull(categoryId, "garbage.category.id.is.null");
    }
}
