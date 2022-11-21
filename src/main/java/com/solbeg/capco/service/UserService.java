package com.solbeg.capco.service;

import com.solbeg.capco.dto.FeatureResponse;
import com.solbeg.capco.dto.UserRequest;
import com.solbeg.capco.dto.UserResponse;
import com.solbeg.capco.entity.Feature;
import com.solbeg.capco.entity.User;
import com.solbeg.capco.mapper.FeatureMapper;
import com.solbeg.capco.mapper.UserMapper;
import com.solbeg.capco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final FeatureMapper featureMapper;
    private final UserRepository userRepository;
    private final FeatureService featureService;

    public Long createUser(UserRequest userRequest) {
        var opt = userRepository.findByLogin(userRequest.getLogin());
        if (opt.isEmpty()) {
            User user = userRepository.save(userMapper.toEntity(userRequest));
            return user.getId();
        }
        throw new RuntimeException("Feature already exists");
    }

    public void enableFeature(Long userId, Long featureId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Feature feature = featureService.getFeature(featureId);
        user.enableFeature(feature);
        //userRepository.save(user);
    }

    protected String currentUserName(Authentication authentication) {
        if (authentication != null) {
            return authentication.getName();
        } else {
            throw new RuntimeException("Cannot get the current user");
        }
    }

    public Set<FeatureResponse> getAllEnabledFeatures() {
        return userRepository.findAll().stream().flatMap(user -> user.getEnabledFeatures().stream().map(featureMapper::toResponse)).collect(Collectors.toSet());
    }

    public Set<FeatureResponse> getFeaturesForUser(Long userId) {
        return userRepository.findById(userId).orElseThrow().getEnabledFeatures().stream().map(featureMapper::toResponse).collect(Collectors.toSet());
    }

    public Set<FeatureResponse> getFeaturesForUser(String login) {
        return getFeaturesForUser(userRepository.findByLogin(login).orElseThrow().getId());
    }
}
