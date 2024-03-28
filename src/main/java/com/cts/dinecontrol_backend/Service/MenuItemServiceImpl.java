package com.cts.dinecontrol_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dinecontrol_backend.Repository.ManagerRepo;
import com.cts.dinecontrol_backend.Repository.MenuItemRepo;
import com.cts.dinecontrol_backend.dtolayer.MenuItemDTO;
import com.cts.dinecontrol_backend.models.Manager;
import com.cts.dinecontrol_backend.models.MenuItem;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    @Autowired
    private MenuItemRepo menuItemRepo;
    
    @Autowired
    private ManagerRepo managerRepo;

    @Override
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepo.findAll().stream().map(menu-> new MenuItemDTO(menu.getManager().getManagerId(), menu.getGroupName(), menu.getName(), menu.getPrice(), menu.getTaste(), menu.getPhotoUrl()  )).toList();
    }

    @Override
    public MenuItem getMenuItemById(int itemId) {
        return menuItemRepo.findById(itemId).orElse(null);
    }

    @Override
    public void addMenuItem(MenuItemDTO menuItemDTO) {

    	Optional<Manager> manager = managerRepo.findById(menuItemDTO.managerId());
        if(manager.isPresent()) {
        	MenuItem menuItem = MenuItem.builder()
        			.groupName(menuItemDTO.groupName())
        			.name(menuItemDTO.name())
        			.price(menuItemDTO.price())
        			.photoUrl(menuItemDTO.photoUrl())
        			.taste(menuItemDTO.taste())
        			.manager(manager.get())
        			.build();

         	MenuItem menu = menuItemRepo.save(menuItem);

        }
        
      }

    @Override
    public void updateMenuItem(int itemId, MenuItem menuItem) {
//    	menuItemRepo.updateMenuItem(menuItem.getManager(), menuItem.getGroupName(),menuItem.getName(), menuItem.getPhotoUrl(),menuItem.getPrice(), menuItem.getTaste(),  menuItem.getItemId());

    }

    @Override
    public void deleteMenuItem(int itemId) {
        menuItemRepo.deleteById(itemId);
    }

    @Override
    public List<MenuItem> getMenuItemsWithVeg() {
        return menuItemRepo.findByGroupNameContainingIgnoreCase("veg");
    }

    @Override
    public List<MenuItem> getMenuItemsWithNonVeg() {
        return menuItemRepo.findByGroupNameContainingIgnoreCase("nonveg");
    }
}


