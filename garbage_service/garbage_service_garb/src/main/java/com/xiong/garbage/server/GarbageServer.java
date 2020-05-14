package com.xiong.garbage.server;

import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import com.xiong.garbage.api.request.*;
import com.xiong.garbage.api.response.GarbageInfo;

import java.util.List;

public interface GarbageServer {
    /**
     * 垃圾分页
     */
    Response<PageResult<GarbageInfo>> paging(GarbagePagingRequest request);

    Response<Long> create(GarbageCreateRequest request);

    Response<Boolean> update(GarbageUpdateRequest request);

    Response<Boolean> delete(GarbageDeleteRequest request);

    Response<GarbageInfo> findById(Long id);

    Response<List<GarbageInfo>> findByName(GarbageFindByNameRequest request);
}
