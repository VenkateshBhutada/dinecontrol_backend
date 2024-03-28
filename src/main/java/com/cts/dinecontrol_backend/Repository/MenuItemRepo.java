package com.cts.dinecontrol_backend.Repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.dinecontrol_backend.models.Manager;
import com.cts.dinecontrol_backend.models.MenuItem;

import jakarta.transaction.Transactional;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByGroupName(String groupName);
    List<MenuItem> findByTasteContainingIgnoreCase(String taste);
    List<MenuItem> findByGroupNameContainingIgnoreCase(String groupName);
	
//	@Transactional
//    @Modifying
//    @Query("UPDATE MenuItem t SET t.manager_id = ?1 ,t.groupName = ?2, t.name = ?3 ,t.photoUrl = ?4, t.price = ?5, t.taste =?6  WHERE t.itemId = ?7 ")
//	void updateMenuItem(Manager manager, String groupName, String name, String photoUrl, BigDecimal price, String taste,
//			int itemId);
}
