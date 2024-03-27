package com.cts.dinecontrol_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cts.dinecontrol_backend.models.TableType;

import jakarta.transaction.Transactional;

public interface TableTypeRepo extends JpaRepository<TableType, Integer> {
   
	 	@Transactional
	    @Modifying
	    @Query("UPDATE TableType t SET t.typeName = ?1, t.capacity = ?2 WHERE t.typeId = ?3")
	    void updateTableType(String typeName, int capacity, int typeId);

}