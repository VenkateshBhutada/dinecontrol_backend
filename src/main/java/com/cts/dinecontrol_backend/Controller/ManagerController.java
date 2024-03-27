package com.cts.dinecontrol_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.dinecontrol_backend.Service.ManagerService;
import com.cts.dinecontrol_backend.dtolayer.LoginManagerDTO;
import com.cts.dinecontrol_backend.dtolayer.ManagerResponseDTO;
import com.cts.dinecontrol_backend.models.Manager;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/{email}")
    public ResponseEntity<Manager> getManagerByEmail(@PathVariable String email) {
        Manager manager = managerService.getManagerByEmail(email);
        return ResponseEntity.ok(manager);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerManager(@RequestBody Manager manager) {
        managerService.registerManager(manager);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<ManagerResponseDTO> loginManager(@RequestBody LoginManagerDTO loginManagerDTO) {
        ManagerResponseDTO response = managerService.loginManager(loginManagerDTO);
        return ResponseEntity.status(response.status()).body(response);
    }
}

