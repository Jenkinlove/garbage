package com.xiong.city.model.converter;

import com.xiong.city.api.request.CityCreateRequest;
import com.xiong.city.api.request.CityUpdateRequest;
import com.xiong.city.api.response.CityInfo;
import com.xiong.city.model.City;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CityApiConverter {

    CityInfo get(City city);

    City get(CityCreateRequest request);

    City get(CityUpdateRequest request);

}
