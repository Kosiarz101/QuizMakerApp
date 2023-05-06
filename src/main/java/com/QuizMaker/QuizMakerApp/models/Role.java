package com.QuizMaker.QuizMakerApp.models;

import com.QuizMaker.QuizMakerApp.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Role extends JPAEntityBase{

    @Id
    @GeneratedValue
    private Long entityId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleEnum name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"roles"})
    private Set<Privilege> privileges = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    @JsonIgnoreProperties({"role"})
    private Set<AppUser> users;
}
