package com.cts.dinecontrol_backend.Service;

import java.util.List;

import com.cts.dinecontrol_backend.models.TableReservation;

public interface TableReservationService {
    List<TableReservation> getAllReservations();
    List<TableReservation> getReservationsByUserId(int userId);
    boolean makeReservation(TableReservation reservation);
    void updateReservation(TableReservation reservation);
    void deleteReservation(int reservationId);
    boolean confirmTable(int userId);
    boolean acceptTable(int tableId,int userId);
    boolean declineTable(int tableId, int userId);
    boolean confirmTable1(int userId);
}
