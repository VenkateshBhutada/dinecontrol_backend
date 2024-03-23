package com.cts.dinecontrol_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.dinecontrol_backend.models.TableReservation;

public interface TableReservationRepo extends JpaRepository<TableReservation, Integer> {
    List<TableReservation> findByUserId(int userId);

	boolean confirmTable(int userId);

	boolean confirmTable1(int userId);
	
	@Query(
			value = "update table_reservation set status = :status where table_id = :tableId and user_id = :userId",
			nativeQuery = true
			)
//	public boolean acceptTable(String status, int tableId, int userId);

//	public boolean declineTable(String status, int tableId, int userId);

	public boolean acceptTable(String status, int tableId, int userId);

	public boolean declineTable(String status, int tableId, int userId);

	
}
