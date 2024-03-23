package com.cts.dinecontrol_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.dinecontrol_backend.models.User;


public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
