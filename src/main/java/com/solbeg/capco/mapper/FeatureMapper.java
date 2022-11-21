package com.solbeg.capco.mapper;

import com.solbeg.capco.dto.FeatureRequest;
import com.solbeg.capco.dto.FeatureResponse;
import com.solbeg.capco.entity.Feature;
import org.mapstruct.Mapper;

@Mapper
public interface FeatureMapper {
    Feature toEntity(FeatureRequest source);

    FeatureResponse toResponse(Feature source);
}
