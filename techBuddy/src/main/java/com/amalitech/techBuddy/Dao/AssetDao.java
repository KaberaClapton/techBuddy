package com.amalitech.techBuddy.Dao;

import com.amalitech.techBuddy.entity.Asset;
import com.amalitech.techBuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetDao extends JpaRepository<Asset, Long> {
    List<Asset> findByOwner(User owner);
    Asset findByDescriptionLike(String description);
}
