package com.xiong.garbage.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GarbageFindByNameRequest extends AbstractRequest {
    private static final long serialVersionUID = -6492330451012876024L;

    private String name;

    private Integer cityId;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(name, "garbage.name.is.null");
        Assert.nonNull(cityId, "city.id.is.null");
    }
}
