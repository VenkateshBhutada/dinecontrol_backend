package com.cts.dinecontrol_backend.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dinecontrol_backend.Repository.TableReservationRepo;
import com.cts.dinecontrol_backend.models.ReservationStatus;
import com.cts.dinecontrol_backend.models.TableReservation;

@Service
public class TableReservationServiceImpl implements TableReservationService {
    @Autowired
    private TableReservationRepo tableReservationRepo;

    @Override
    public List<TableReservation> getAllReservations() {
        return tableReservationRepo.findAll();
    }

    @Override
    public List<TableReservation> getReservationsByUserId(int userId) {
        return tableReservationRepo.findByUserId(userId);
    }

    @Override
    public boolean makeReservation(TableReservation reservation) {
        try {
            tableReservationRepo.save(reservation);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to make reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void updateReservation(TableReservation reservation) {
        tableReservationRepo.save(reservation);
    }

    @Override
    public void deleteReservation(int reservationId) {
        tableReservationRepo.deleteById(reservationId);
    }

    @Override
    public boolean confirmTable(int userId) {
        // Implement confirm table logic if needed
    	return tableReservationRepo.confirmTable(userId);
    }

    @Override
    public boolean confirmTable1(int userId) {
        // Implement confirm table 1 logic if needed
    	return tableReservationRepo.confirmTable1(userId);
    }

    @Override
    public boolean acceptTable(int tableId, int userId) {
        return tableReservationRepo.acceptTable(ReservationStatus.ACCEPTED, tableId, userId);
    }

    @Override
    public boolean declineTable(int tableId, int userId) {
        return tableReservationRepo.declineTable(ReservationStatus.DECLINED, tableId, userId);
    }

    
    
}


