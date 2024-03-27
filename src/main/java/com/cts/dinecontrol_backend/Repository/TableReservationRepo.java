package com.cts.dinecontrol_backend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.dinecontrol_backend.models.TableReservation;

public interface TableReservationRepo extends JpaRepository<TableReservation, Integer> {

//    List<TableReservation> findByUserId(int userId);
//
//	boolean confirmTable(int userId);
//
//	boolean confirmTable1(int userId);
	
//	@Query(
//			value = "update table_reservation set status = ?1 where table_id = ?2 and user_id = ?3",
//			nativeQuery = true
//			)
//
//
//	public boolean acceptTable( String status, int tableId, int userId);
//
//	public boolean declineTable(String status, int tableId, int userId);

	
}
