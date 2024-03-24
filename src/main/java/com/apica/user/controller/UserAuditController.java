package com.apica.user.controller;

import com.apica.user.domain.entity.UserAuditEntity;
import com.apica.user.service.UserAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-audits")
public class UserAuditController {

    private final UserAuditService userAuditService;

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<UserAuditEntity>>
    getPaginatedAudits(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<UserAuditEntity> audits = userAuditService.findPaginated(page, size);
        return ResponseEntity.ok(audits);
    }
}
