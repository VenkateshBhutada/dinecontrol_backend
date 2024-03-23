package com.cts.dinecontrol_backend.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.dinecontrol_backend.models.MenuItem;

public interface MenuItemRepo extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByGroupName(String groupName);
    List<MenuItem> findByTasteContainingIgnoreCase(String taste);
    List<MenuItem> findByGroupNameContainingIgnoreCase(String groupName);
}
