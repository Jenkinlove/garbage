package com.xiong.city.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityCreateRequest extends AbstractRequest {
    private static final long serialVersionUID = -6714645934244543362L;

    private String name;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(name, "city.name.is.null");
    }
}
