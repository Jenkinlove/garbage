package com.xiong.problem.api.request;

import com.xiong.common.utils.AbstractRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProblemFindByRandRequest extends AbstractRequest {
    private static final long serialVersionUID = -382124473781224559L;

    private List<Long> ids;
}
