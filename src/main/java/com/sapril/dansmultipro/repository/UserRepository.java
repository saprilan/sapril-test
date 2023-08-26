package com.sapril.dansmultipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapril.dansmultipro.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
