package com.xiong.garbage.model.converter;

import com.xiong.garbage.api.request.GarbageCreateRequest;
import com.xiong.garbage.api.request.GarbageUpdateRequest;
import com.xiong.garbage.api.response.GarbageInfo;
import com.xiong.garbage.model.Garbage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GarbageApiConverter {

    GarbageInfo get(Garbage garbage);

    Garbage get(GarbageCreateRequest request);

    Garbage get(GarbageUpdateRequest request);

}
