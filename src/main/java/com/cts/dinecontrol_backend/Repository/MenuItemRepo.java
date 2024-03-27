package com.cts.dinecontrol_backend.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.dinecontrol_backend.models.MenuItem;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByGroupName(String groupName);
    List<MenuItem> findByTasteContainingIgnoreCase(String taste);
    List<MenuItem> findByGroupNameContainingIgnoreCase(String groupName);
}
