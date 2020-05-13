package com.xiong.article.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDeleteRequest extends AbstractRequest {
    private static final long serialVersionUID = -4917930021068637568L;

    private List<Long> ids;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.notEmpty(ids, "article.ids.is.empty");
    }
}
