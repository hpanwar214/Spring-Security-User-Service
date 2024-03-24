

package com.apica.user.domain.repository;

import com.apica.user.domain.entity.UserAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuditRepository extends JpaRepository<UserAuditEntity, Long> {
    Optional<UserAuditEntity> findByEmail(String email);
}
