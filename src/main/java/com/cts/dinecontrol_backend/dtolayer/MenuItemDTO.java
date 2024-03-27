package com.cts.dinecontrol_backend.dtolayer;

import java.math.BigDecimal;

public record MenuItemDTO(
		int managerId,
		String groupName,
		String name,
		BigDecimal price,
		String taste,
		String photoUrl
		) {

}
