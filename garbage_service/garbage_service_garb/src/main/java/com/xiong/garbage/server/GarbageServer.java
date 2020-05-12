package com.xiong.garbage.server;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.GarbageCreateRequest;
import com.xiong.garbage.api.request.GarbageDeleteRequest;
import com.xiong.garbage.api.request.GarbagePagingRequest;
import com.xiong.garbage.api.request.GarbageUpdateRequest;
import com.xiong.garbage.api.response.GarbageInfo;

public interface GarbageServer {
    /**
     * 垃圾分页
     */
    Response<PageResult<GarbageInfo>> paging(GarbagePagingRequest request);

    Response<Long> create(GarbageCreateRequest request);

    Response<Boolean> update(GarbageUpdateRequest request);

    Response<Boolean> delete(GarbageDeleteRequest request);

    Response<GarbageInfo> findById(Long id);
}
