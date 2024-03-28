package com.cts.dinecontrol_backend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cts.dinecontrol_backend.models.TableReservation;

public interface TableReservationRepo extends JpaRepository<TableReservation, Integer> {


	

	    @Transactional
	    @Modifying
	    @Query(
	    		value = "UPDATE table_reservation tr SET tr.status ='Accepted' WHERE tr.reservation_id = :reservationId ",
	    		nativeQuery = true)
	    
	    void acceptReservation(@Param("reservationId") Integer reservationId);

	    @Transactional
	    @Modifying
	    @Query(value = "UPDATE table_reservation tr SET tr.status = 'Declined' WHERE tr.reservation_id = :reservationId ",
	    nativeQuery = true)
	    void declineReservation(@Param("reservationId") Integer reservationId);
	


	
}
