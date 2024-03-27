package com.cts.dinecontrol_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.dinecontrol_backend.Service.MenuItemService;
import com.cts.dinecontrol_backend.dtolayer.MenuItemDTO;
import com.cts.dinecontrol_backend.models.MenuItem;

import java.util.List;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems() {
        List<MenuItemDTO> menuItems = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/veg")
    public ResponseEntity<List<MenuItem>> getMenuItemsWithVeg() {
        List<MenuItem> menuItems = menuItemService.getMenuItemsWithVeg();
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/nonveg")
    public ResponseEntity<List<MenuItem>> getMenuItemsWithNonVeg() {
        List<MenuItem> menuItems = menuItemService.getMenuItemsWithNonVeg();
        return ResponseEntity.ok(menuItems);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItemDTO menuItem) {
        menuItemService.addMenuItem(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Menu added successfully.");
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Void> updateMenuItem(@PathVariable int itemId, @RequestBody MenuItem menuItem) {
        menuItem.setItemId(itemId);
        menuItemService.updateMenuItem(menuItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int itemId) {
        menuItemService.deleteMenuItem(itemId);
        return ResponseEntity.ok().build();
    }
}

