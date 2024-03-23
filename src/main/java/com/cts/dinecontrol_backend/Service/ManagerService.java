package com.cts.dinecontrol_backend.Service;

import java.util.List;

import com.cts.dinecontrol_backend.dtolayer.LoginManagerDTO;
import com.cts.dinecontrol_backend.dtolayer.ManagerResponseDTO;
import com.cts.dinecontrol_backend.models.Manager;
import com.cts.dinecontrol_backend.models.MenuItem;

public interface ManagerService{
	Manager getManagerByEmail (String email);
	void registerManager(Manager manager);
	ManagerResponseDTO loginManager(LoginManagerDTO loginManagerDTO);
	void addMenuItem(MenuItem menuItem);
	List<MenuItem> getAllMenuItems();
}