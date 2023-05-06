package com.QuizMaker.QuizMakerApp.models;

import com.QuizMaker.QuizMakerApp.enums.PrivilegeEnum;
import com.fasterxml.jackson.annotation.*;
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
public class Privilege extends JPAEntityBase {

    @Id
    @GeneratedValue
    private Long entityId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PrivilegeEnum name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "privileges")
    @JsonIgnoreProperties({"privileges"})
    private Set<Role> roles = new HashSet<>();
}
