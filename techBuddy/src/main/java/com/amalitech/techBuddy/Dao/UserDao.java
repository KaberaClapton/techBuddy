package com.amalitech.techBuddy.Dao;

import com.amalitech.techBuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
