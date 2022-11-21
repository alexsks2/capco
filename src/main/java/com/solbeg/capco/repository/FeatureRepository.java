package com.solbeg.capco.repository;

import com.solbeg.capco.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    Optional<Feature> findById(Long id);
    Optional<Feature> findByName(String name);
}
