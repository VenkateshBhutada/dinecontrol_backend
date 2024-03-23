package com.cts.dinecontrol_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.dinecontrol_backend.models.TableType;

public interface TableTypeRepo extends JpaRepository<TableType, Integer> {
   
}