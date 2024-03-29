package com.cts.dinecontrol_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.dinecontrol_backend.Service.TableTypeService;
import com.cts.dinecontrol_backend.models.TableType;

import java.util.List;

@RestController
@RequestMapping("/table-types")
public class TableTypeController {

    @Autowired
    private TableTypeService tableTypeService;

    @GetMapping
    public ResponseEntity<List<TableType>> getAllTableTypes() {
        List<TableType> tableTypes = tableTypeService.getAllTableTypes();
        return ResponseEntity.ok(tableTypes);
    }

    @PostMapping
    public ResponseEntity<String> addTableType(@RequestBody TableType tableType) {
        tableTypeService.addTableType(tableType);
        return ResponseEntity.status(HttpStatus.CREATED).body("Table added successfully!");
    }

    @PutMapping("/update-table-type")
    public ResponseEntity<String> updateTableType(@RequestParam("type_id") int typeId, @RequestBody TableType tableType) {
        System.out.println(typeId);
    	tableTypeService.updateTableType(typeId, tableType);
        return ResponseEntity.ok().body("Table successfully updated");
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<String> deleteTableType(@PathVariable int typeId) {
        tableTypeService.deleteTableType(typeId);
        return ResponseEntity.ok().body("Table deleted successfully!");
    }
}
