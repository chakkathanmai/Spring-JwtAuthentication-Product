package com.product.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class JwtUser {
    @Id
    @GeneratedValue
    private Integer userId;
    private String username;
    private String password;

    public JwtUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
