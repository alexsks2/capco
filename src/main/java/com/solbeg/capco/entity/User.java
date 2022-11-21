package com.solbeg.capco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "user_feature", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private Set<Feature> enabledFeatures = new HashSet<>();

    public void enableFeature(Feature feature) {
        enabledFeatures.add(feature);
    }

}
