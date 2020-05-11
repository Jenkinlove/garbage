package com.xiong.city.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = -3902192790145061410L;

    private Integer id;

    private String name;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(id, "city.id.is.null");
        Assert.nonNull(name, "city.name.is.null");
    }
}
