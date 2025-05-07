package com.dwitech.kc.cxs.frontend.entity.db;


import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.json.bind.JsonbBuilder.create;

@Entity
@Table(name = "user")
public class User  extends PanacheEntity implements RenardeUser {
    @OneToOne(mappedBy = "user") public Credentials credentials;

    public static User findByCredentials(final String username) {
        return User.find("credentials.username = ?1", username).firstResult();
    }

    public static User findByUsername(final String username) {
        return findByCredentials(username);
    }

    @Override
    public Set<String> roles() {
        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        return roles;
    }

    @Override
    public String userId() {
        return credentials.username;
    }

    @Override
    public boolean registered() {
        return true;
    }

    @Override public String toString() { return create().toJson(this); }
}