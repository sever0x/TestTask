package com.shdwraze.testtask.mapper;

import com.shdwraze.testtask.model.entity.UserEntity;
import com.shdwraze.testtask.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(UserRequest userRequest);
}
