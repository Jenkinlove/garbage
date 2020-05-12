package com.xiong.garbage.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GarbageUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = -5458690570501803198L;

    private Long id;

    private String name;

    private Long cityId;

    private Long categoryId;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(id, "garbage.id.is.null");
    }
}
