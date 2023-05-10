package com.QuizMaker.QuizMakerApp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class AppUser extends JPAEntityBase implements UserDetails {

    @Id
    @GeneratedValue
    private Long entityId;

    @Column(nullable = false, unique = true)
    private String email;

    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnoreProperties({"users"})
    private Role role;

    @JsonIgnoreProperties({"owner"})
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "owner")
    private Set<Quiz> quizzes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null) {
            return role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName().getValue()))
                    .collect(Collectors.toSet());
        }
        else {
            return new HashSet<>();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
