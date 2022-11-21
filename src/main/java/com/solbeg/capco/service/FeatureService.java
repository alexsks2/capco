package com.solbeg.capco.service;

import com.solbeg.capco.dto.FeatureRequest;
import com.solbeg.capco.entity.Feature;
import com.solbeg.capco.mapper.FeatureMapper;
import com.solbeg.capco.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureMapper featureMapper;
    private final FeatureRepository featureRepository;

    public Long createFeature(FeatureRequest featureRequest) {
        var opt = featureRepository.findByName(featureRequest.getName());
        if (opt.isEmpty()) {
            Feature feature = featureRepository.save(featureMapper.toEntity(featureRequest));
            return feature.getId();
        }
        throw new RuntimeException("Feature already exists");
    }

    public Feature getFeature(Long featureId) {
        return featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature doesn't exist"));
    }


}
