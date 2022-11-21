package com.solbeg.capco.controller;

import com.solbeg.capco.dto.FeatureResponse;
import com.solbeg.capco.dto.UserRequest;
import com.solbeg.capco.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Long> createUser(UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{userId}/enable/{featureId}")
    public ResponseEntity<Void> enableFeatureForUser(@PathVariable Long userId, @PathVariable Long featureId) {
        userService.enableFeature(userId, featureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/features/{userId}")
    public ResponseEntity<Set<FeatureResponse>> getFeaturesForUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getFeaturesForUser(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/features/currentUser")
    public ResponseEntity<Set<FeatureResponse>> getFeaturesForUser(Authentication authentication) {
        return new ResponseEntity<> (userService.getFeaturesForUser(authentication.getName()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/features/all")
    public ResponseEntity<Set<FeatureResponse>> getAllEnabledFeatures() {
        return new ResponseEntity<> (userService.getAllEnabledFeatures(), HttpStatus.OK);
    }
}
