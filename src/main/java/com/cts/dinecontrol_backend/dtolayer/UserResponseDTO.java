package com.cts.dinecontrol_backend.dtolayer;

import org.springframework.http.HttpStatus;

public record UserResponseDTO(HttpStatus status,
		int userId,
		String message) {

}
