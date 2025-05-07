package com.dwitech.kc.cxs.frontend.entity.db;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "credentials")
public class Credentials  extends PanacheEntity {
    @Column(name = "username", nullable = false, length = 50) public String username;
    @Column(name = "password", nullable = false, length = 100) public String password;
    @OneToOne @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false) public User user;

    public static Credentials findByUsername(final String username) {
        return Credentials.find("username", username).firstResult();
    }
}