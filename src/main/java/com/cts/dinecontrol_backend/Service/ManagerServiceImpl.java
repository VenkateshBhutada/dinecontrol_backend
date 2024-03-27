package com.cts.dinecontrol_backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dinecontrol_backend.Repository.ManagerRepo;
import com.cts.dinecontrol_backend.Repository.MenuItemRepo;
import com.cts.dinecontrol_backend.dtolayer.LoginManagerDTO;
import com.cts.dinecontrol_backend.dtolayer.ManagerResponseDTO;
import com.cts.dinecontrol_backend.models.Manager;
import com.cts.dinecontrol_backend.models.MenuItem;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;

    @Override
    public Manager getManagerByEmail(String email) {
        return managerRepo.findByEmail(email);
    }

    @Override
    public void registerManager(Manager manager) {
    
        managerRepo.save(manager);
    }

    @Override
    public ManagerResponseDTO loginManager(LoginManagerDTO loginManagerDTO) {
        
        return null;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        menuItemRepo.save(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepo.findAll();
    }
}
