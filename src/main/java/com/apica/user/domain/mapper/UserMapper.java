

package com.apica.user.domain.mapper;

import com.apica.user.domain.entity.UserAuditEntity;
import com.apica.user.domain.entity.UserEntity;
import com.apica.user.domain.inbound.UserAuditEvent;
import com.apica.user.domain.inbound.UserCreationRequest;
import com.apica.user.domain.inbound.UserUpdateRequest;
import com.apica.user.domain.outbound.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponse toUserResponse(UserEntity user);
    UserEntity toUserEntity(UserCreationRequest user);
    UserEntity updateUserEntity(UserUpdateRequest user, @MappingTarget UserEntity userEntity);
    UserAuditEntity toUserAuditEntity(UserAuditEvent userAuditEvent);


}
