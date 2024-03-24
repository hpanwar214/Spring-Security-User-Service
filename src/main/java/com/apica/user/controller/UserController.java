

package com.apica.user.controller;

import com.apica.user.domain.inbound.UserCreationRequest;
import com.apica.user.domain.inbound.UserUpdateRequest;
import com.apica.user.domain.outbound.UserResponse;
import com.apica.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UserResponse> registerUser(@RequestBody UserCreationRequest userCreationRequest) {
        return ResponseEntity.ok(userService.registerUser(userCreationRequest));
    }
    @PutMapping("/email/{email}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    ResponseEntity<UserResponse> updateUserDetails(@RequestBody UserUpdateRequest userUpdateRequest,@PathVariable String email) {
        return ResponseEntity.ok(userService.updateUserDetails(email,userUpdateRequest));
    }
    @GetMapping("/email/{email}")
    ResponseEntity<UserResponse> getUserDetails(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }
    @DeleteMapping("email/{email}")
    ResponseEntity<UserResponse> deleteUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.deleteUser(email));
    }
}
