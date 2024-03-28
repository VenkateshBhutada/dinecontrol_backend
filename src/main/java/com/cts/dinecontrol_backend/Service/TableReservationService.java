package com.cts.dinecontrol_backend.Service;

import java.util.List;

import com.cts.dinecontrol_backend.dtolayer.TableReservationDTO;
import com.cts.dinecontrol_backend.models.TableReservation;

public interface TableReservationService {
    List<TableReservation> getAllReservations();
    List<TableReservation> getReservationsByUserId(int userId);
    boolean makeReservation(TableReservationDTO tableReservationDTO);

    void deleteReservation(int reservationId);

	void  acceptReservation(int reservationId);
	void declineReservation(int reservationId);
}
