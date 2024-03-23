package com.cts.dinecontrol_backend.dtolayer;

import org.springframework.http.HttpStatus;

public record ManagerResponseDTO(
		HttpStatus status,
		String message,
		int managerid
		) {

}
