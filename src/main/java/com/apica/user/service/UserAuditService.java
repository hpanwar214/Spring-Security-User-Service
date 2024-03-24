

package com.apica.user.service;

import com.apica.user.domain.entity.UserAuditEntity;
import com.apica.user.domain.repository.UserAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuditService {
    private final UserAuditRepository userAuditRepository;

    public Page<UserAuditEntity> findPaginated(int page, int size) {
        return userAuditRepository.findAll(PageRequest.of(page, size));
    }
}
