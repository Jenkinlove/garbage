package com.xiong.article.api.request;

import com.xiong.common.utils.AbstractRequest;
import com.xiong.common.utils.Assert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleCreateRequest extends AbstractRequest {
    private static final long serialVersionUID = 5412503829055415976L;

    private String name;

    private String content;

    private Integer articleType;

    @Override
    public void checkParam() {
        super.checkParam();
        Assert.nonNull(name, "article.name.is.null");
        Assert.nonNull(content, "article.content.is.null");
        Assert.nonNull(articleType, "article.type.is.null");
    }
}
