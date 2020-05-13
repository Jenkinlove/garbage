package com.xiong.article.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlePagingRequest extends AbstractRequest {
    private static final long serialVersionUID = 8201265828715841508L;

    private Integer page;

    private Integer pageSize;

    private String name;

    private Integer articleType;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(page, "page.is.null");
        Assert.nonNull(pageSize, "page.size.is.null");
    }

}
