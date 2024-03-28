package com.cts.dinecontrol_backend.dtolayer;

import java.time.LocalDate;
import java.time.LocalTime;

public record TableReservationDTO(
		String userName,
		int tableId,
		LocalDate reservationDate,
		LocalTime reservationTime,
		String status,
		int userId
		) {
	
	
	
}
