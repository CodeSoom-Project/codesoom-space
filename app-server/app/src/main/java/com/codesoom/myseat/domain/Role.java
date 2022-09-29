package com.codesoom.myseat.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @Getter
    private String roleName;

    public Role(
            String email, 
            String roleName
    ) {
        this.email = email;
        this.roleName = roleName;
    }
}
