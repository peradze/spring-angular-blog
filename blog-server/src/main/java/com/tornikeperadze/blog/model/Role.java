package com.tornikeperadze.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Roles name;

    public Role(Roles role) {
        name = role;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }

    public enum Roles {
        ROLE_ADMIN,
        ROLE_USER
    }
}
