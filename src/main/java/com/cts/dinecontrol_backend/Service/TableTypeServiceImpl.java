package com.cts.dinecontrol_backend.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dinecontrol_backend.Repository.TableTypeRepo;
import com.cts.dinecontrol_backend.models.TableType;

@Service
public class TableTypeServiceImpl implements TableTypeService {
    @Autowired
    private TableTypeRepo tableTypeRepo;

    @Override
    public List<TableType> getAllTableTypes() {
        return tableTypeRepo.findAll();
    }

    @Override
    public TableType getTableTypeById(int typeId) {
        return tableTypeRepo.findById(typeId).orElse(null);
    }

    @Override
    public void addTableType(TableType tableType) {
        tableTypeRepo.save(tableType);
    }
    @Override
    public void updateTableType(int typeId, TableType tableType) {
    
        tableTypeRepo.updateTableType(tableType.getTypeName(), tableType.getCapacity(),typeId);
    }

    @Override
    public void deleteTableType(int typeId) {
        tableTypeRepo.deleteById(typeId);
    }
}
