package com.cts.dinecontrol_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.dinecontrol_backend.models.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
    public Manager findByEmail(String email);
    
}
