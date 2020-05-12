package com.xiong.garbage.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GarbageDeleteRequest extends AbstractRequest {
    private static final long serialVersionUID = -6947661517130642205L;


    private List<Long> ids;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.notEmpty(ids, "garbage.ids.is.empty");
    }
}
