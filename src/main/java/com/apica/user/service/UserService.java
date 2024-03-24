

package com.apica.user.service;

import com.apica.user.domain.entity.UserEntity;
import com.apica.user.domain.exception.UserException;
import com.apica.user.domain.inbound.UserAuditEvent;
import com.apica.user.domain.inbound.UserCreationRequest;
import com.apica.user.domain.inbound.UserUpdateRequest;
import com.apica.user.domain.mapper.UserMapper;
import com.apica.user.domain.outbound.UserResponse;
import com.apica.user.domain.repository.UserRepository;
import com.apica.user.producer.AuditEventProducer;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuditEventProducer auditEventProducer;
    private final  BCryptPasswordEncoder passwordEncoder;
    private static final UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    public UserResponse registerUser(UserCreationRequest userCreationRequest) {
        Optional<UserEntity> user = userRepository.findByEmail(userCreationRequest.getEmail());
        if(user.isPresent()){
            throw new UserException("Email already exist found", HttpStatus.BAD_REQUEST);
        }
        userCreationRequest.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        sendAuditEvent(MAPPER.toUserEntity(userCreationRequest), "User created");
       return MAPPER.toUserResponse(userRepository.save(MAPPER.toUserEntity(userCreationRequest)));
    }
    public UserResponse getUser(String email) {
        Optional<UserEntity> user = getUserEntity(email);
        sendAuditEvent(user.get(), "User fetched");
        return MAPPER.toUserResponse(user.get());
    }

    private Optional<UserEntity> getUserEntity(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public UserResponse updateUserDetails(String email,UserUpdateRequest updateRequest) {
        Optional<UserEntity> user = getUserEntity(email);
        MAPPER.updateUserEntity(updateRequest, user.get());
        sendAuditEvent(user.get(), "User updated");
        return MAPPER.toUserResponse(userRepository.save(user.get()));
    }
    public UserResponse deleteUser(String email) {
        Optional<UserEntity> user = getUserEntity(email);
        userRepository.delete(user.get());
        sendAuditEvent(user.get(), "User deleted");
        return MAPPER.toUserResponse(user.get());
    }
    private void sendAuditEvent(UserEntity userEntity, String action) {
        auditEventProducer.sendAuditEvent(UserAuditEvent.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .createdDate(new Date())
                .action(action)
                .build());
    }


}
