

package com.apica.user.domain.inbound;

import com.apica.user.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
}
