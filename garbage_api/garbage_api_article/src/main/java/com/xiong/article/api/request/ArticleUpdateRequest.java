package com.xiong.article.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleUpdateRequest extends AbstractRequest {
    private static final long serialVersionUID = -6444128896141597654L;

    private Long id;

    private String name;

    private String content;

    private Integer articleType;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(id, "article.id.is.null");
    }
}
