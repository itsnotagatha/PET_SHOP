package com.agata.petshop.repository;

import com.agata.petshop.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
}
