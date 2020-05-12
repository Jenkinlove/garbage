package com.xiong.category.server;

import com.xiong.category.api.request.CategoryCreateRequest;
import com.xiong.category.api.request.CategoryDeleteRequest;
import com.xiong.category.api.request.CategoryPagingRequest;
import com.xiong.category.api.request.CategoryUpdateRequest;
import com.xiong.category.api.response.CategoryInfo;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;

public interface CategoryServer {
    Response<PageResult<CategoryInfo>> paging(CategoryPagingRequest request);

    Response<Long> create(CategoryCreateRequest request);

    Response<Boolean> update(CategoryUpdateRequest request);

    Response<CategoryInfo> findById(Long id);

    Response<Boolean> delete(CategoryDeleteRequest request);
}
