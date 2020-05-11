package com.xiong.city.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityDeleteRequest extends AbstractRequest {
    private static final long serialVersionUID = -3317596431909451633L;

    public List<Integer> ids;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.notEmpty(ids, "city.ids.is.empty");
    }
}
