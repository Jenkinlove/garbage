package com.xiong.city.server.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiong.city.api.request.CityCreateRequest;
import com.xiong.city.api.request.CityDeleteRequest;
import com.xiong.city.api.request.CityPagingRequest;
import com.xiong.city.api.request.CityUpdateRequest;
import com.xiong.city.api.response.CityInfo;
import com.xiong.city.dao.CityMapper;
import com.xiong.city.model.City;
import com.xiong.city.model.converter.CityApiConverter;
import com.xiong.city.server.CityServer;
import com.xiong.common.utils.AssembleDataUtils;
import com.xiong.common.utils.PageResult;
import com.xiong.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class CityServerImpl implements CityServer {

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityApiConverter cityApiConverter;


    @Override
    public Response<PageResult<CityInfo>> paging(CityPagingRequest request) {
        try {
            PageHelper.startPage(request.getPage(), request.getPageSize());
            if (!StringUtils.isEmpty(request.getName())) {
                request.setName(request.getName().trim());
            }
            Page<City> page = (Page<City>) cityMapper.paging(request.getName());
            List<CityInfo> cityInfos = AssembleDataUtils.list2list(page.getResult(), cityApiConverter::get);
            return Response.ok(PageResult.paging(page.getTotal(), cityInfos));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.paging.fail");
        }
    }

    @Override
    public Response<Integer> create(CityCreateRequest request) {
        try {
            City city = cityApiConverter.get(request);
            city.setCreatedAt(new Date());
            cityMapper.insert(city);
            return Response.ok(city.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.create.fail");
        }
    }

    @Override
    public Response<Boolean> update(CityUpdateRequest request) {
        try {
            int updateCount = cityMapper.update(request.getId(), request.getName());
            return Response.ok(updateCount == 1);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.update.fail");
        }
    }

    @Override
    public Response<CityInfo> findById(Integer id) {
        try {
            City city = cityMapper.selectByPrimaryKey(id);
            return Response.ok(cityApiConverter.get(city));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.find.fail");
        }
    }

    public Response<Boolean> checkName(String name) {
        try {
            City city = cityMapper.findByName(name);
            if (city != null) {
                return Response.ok(Boolean.TRUE);
            } else {
                return Response.ok(Boolean.FALSE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("check.name.duplicate.is.fail");
        }
    }

    @Override
    public Response<Boolean> delete(CityDeleteRequest request) {
        try {
            int deleteCount = cityMapper.deleteCities(AssembleDataUtils.list2set(request.getIds()));
            return Response.ok(deleteCount != 0);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("city.delete.fail");
        }
    }
}
