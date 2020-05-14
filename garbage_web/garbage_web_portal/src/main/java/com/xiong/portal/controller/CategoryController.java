package com.xiong.portal.controller;

import com.xiong.category.api.response.CategoryInfo;
import com.xiong.category.service.CategoryFeignClient;
import com.xiong.common.utils.Response;
import com.xiong.common.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portal/category")
public class CategoryController {

    @Autowired
    private CategoryFeignClient categoryFeignClient;


    @GetMapping("/city")
    public Response<List<CategoryInfo>> findByCityId(Long cityId) {
        try {
            if (StringUtils.isEmpty(cityId)) {
                throw new ServiceException("city.id.is.null");
            }
            List<CategoryInfo> categoryInfos = categoryFeignClient.findByCityId(cityId);
            return Response.ok(categoryInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.find.fail");
        }
    }

    @GetMapping("/single")
    public Response<CategoryInfo> findById(Long id) {
        try {
            if (StringUtils.isEmpty(id)) {
                throw new ServiceException("category.id.is.null");
            }
            CategoryInfo categoryInfo = categoryFeignClient.findById(id);
            return Response.ok(categoryInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("category.find.fail");
        }
    }

}
