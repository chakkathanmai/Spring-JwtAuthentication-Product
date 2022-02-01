package com.product.app.repository;

import com.product.app.model.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<JwtUser,Integer> {

    JwtUser findByUsername(String username);
}
