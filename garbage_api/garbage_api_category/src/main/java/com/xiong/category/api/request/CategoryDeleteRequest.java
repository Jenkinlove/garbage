package com.xiong.category.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDeleteRequest extends AbstractRequest {
    private static final long serialVersionUID = 346989271914194465L;

    private List<Long> ids;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.notEmpty(ids, "category.ids.is.empty");
    }
}
