package com.solbeg.capco.dto;

import com.solbeg.capco.entity.Feature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String login;
    private boolean isAdmin;
    private Set<Feature> enabledFeatures = new HashSet<>();
}
