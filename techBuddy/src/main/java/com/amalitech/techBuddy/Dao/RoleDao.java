package com.amalitech.techBuddy.Dao;

import com.amalitech.techBuddy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
