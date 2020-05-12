package com.xiong.category.model.converter;

import com.xiong.category.api.request.CategoryCreateRequest;
import com.xiong.category.api.request.CategoryUpdateRequest;
import com.xiong.category.api.response.CategoryInfo;
import com.xiong.category.model.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CategoryApiConverter {

    CategoryInfo get(Category category);

    Category get(CategoryCreateRequest request);

    Category get(CategoryUpdateRequest request);

}
