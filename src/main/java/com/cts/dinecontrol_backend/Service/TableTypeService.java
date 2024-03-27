package com.cts.dinecontrol_backend.Service;

import com.cts.dinecontrol_backend.models.TableType;

import java.util.List;

public interface TableTypeService {
    List<TableType> getAllTableTypes();
    TableType getTableTypeById(int typeId);
    void addTableType(TableType tableType);
    void updateTableType(int typeId, TableType tableType);
    void deleteTableType(int typeId);
}




