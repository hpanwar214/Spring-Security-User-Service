

package com.apica.user.config;

import com.apica.user.domain.entity.UserEntity;
import com.apica.user.domain.enums.Role;
import com.apica.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SetupConfig
        implements
        ApplicationListener<ContextRefreshedEvent>
{
    Boolean alreadySetup = Boolean.FALSE;
    private final UserRepository userRepository;

    @Value("${apica.email}")
    private String email;
    @Value("${apica.username}")
    private String username;
    @Value("${apica.password}")
    private String password;
    @Value("${apica.phone}")
    private String phoneNumber;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (Boolean.TRUE.equals(alreadySetup))
            return;
        if(userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = UserEntity.builder()
                    .email(email)
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .role(Role.ROLE_ADMIN)
                    .phoneNumber(phoneNumber).build();
            userRepository.save(user);
        }
        alreadySetup = Boolean.TRUE;
    }
}
