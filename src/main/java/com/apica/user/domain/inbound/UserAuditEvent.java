

package com.apica.user.domain.inbound;

import com.apica.user.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuditEvent {
    private String username;
    private String email;
    private Role role;
    private Date createdDate;
    private String action;
}
