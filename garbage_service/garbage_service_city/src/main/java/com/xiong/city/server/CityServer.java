package com.xiong.city.server;

import com.xiong.city.api.request.CityCreateRequest;
import com.xiong.city.api.request.CityDeleteRequest;
import com.xiong.city.api.request.CityPagingRequest;
import com.xiong.city.api.request.CityUpdateRequest;
import com.xiong.city.api.response.CityInfo;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;

import java.util.List;

public interface CityServer {
    Response<PageResult<CityInfo>> paging(CityPagingRequest request);

    Response<Integer> create(CityCreateRequest request);

    Response<Boolean> update(CityUpdateRequest request);

    Response<CityInfo> findById(Integer id);

    Response<Boolean> checkName(String name);

    Response<Boolean> delete(CityDeleteRequest request);

    Response<List<CityInfo>> findAll();
}
