package com.cts.dinecontrol_backend.Service;

import java.util.List;

import com.cts.dinecontrol_backend.dtolayer.MenuItemDTO;
import com.cts.dinecontrol_backend.models.MenuItem;

public interface MenuItemService {
    List<MenuItemDTO> getAllMenuItems();
    MenuItem getMenuItemById(int itemId);
    void addMenuItem(MenuItemDTO menuItem);
    void updateMenuItem(int itemId,MenuItem menuItem);
    void deleteMenuItem(int itemId);
    List<MenuItem> getMenuItemsWithVeg();
   
	List<MenuItem> getMenuItemsWithNonVeg();
}