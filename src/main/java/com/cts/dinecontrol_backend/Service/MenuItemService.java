package com.cts.dinecontrol_backend.Service;

import java.util.List;

import com.cts.dinecontrol_backend.models.MenuItem;

public interface MenuItemService {
    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(int itemId);
    void addMenuItem(MenuItem menuItem);
    void updateMenuItem(MenuItem menuItem);
    void deleteMenuItem(int itemId);
    List<MenuItem> getMenuItemsWithVeg();
   
	List<MenuItem> getMenuItemsWithNonVeg();
}