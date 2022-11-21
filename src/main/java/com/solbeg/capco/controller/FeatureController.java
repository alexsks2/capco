package com.solbeg.capco.controller;

import com.solbeg.capco.dto.FeatureRequest;
import com.solbeg.capco.service.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/feature")
public class FeatureController {

    private final FeatureService featureService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Long> create(FeatureRequest featureRequest) {
        return new ResponseEntity<>(featureService.createFeature(featureRequest), HttpStatus.CREATED);
    }
}
