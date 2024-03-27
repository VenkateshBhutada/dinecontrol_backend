package com.cts.dinecontrol_backend.Service;


import java.util.List;
import java.util.Optional;

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
//        return tableReservationRepo.findByUserId(userId);
    	return null;
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

//    @Autowired
//    private TableReservationRepo tableReservationRepo;

    public TableReservation createReservation(TableReservation reservation) {
        reservation.setStatus(ReservationStatus.PENDING);
        return tableReservationRepo.save(reservation);
    }

    public TableReservation updateReservationStatus(int reservationId, ReservationStatus status) {
      Optional<TableReservation> reservation = tableReservationRepo.findById(reservationId);
      if(reservation.isPresent()) {
    	  
    	  reservation.get().setStatus(status);
      } 
        return tableReservationRepo.save(reservation.get());
    }


    @Override
    public void deleteReservation(int reservationId) {
        tableReservationRepo.deleteById(reservationId);
    }

	@Override
	public TableReservation updateReservationStatus(int reservationId, String aCCEPTED) {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    public boolean confirmTable(int userId) {
//        // Implement confirm table logic if needed
////    	return tableReservationRepo.confirmTable(userId);
//    	return true;
//    }
//
//    @Override
//    public boolean confirmTable1(int userId) {
//        // Implement confirm table 1 logic if needed
////    	return tableReservationRepo.confirmTable1(userId);
//    	return true;
//    }
//
//    @Override
//    public boolean acceptTable(int tableId, int userId) {
////        return tableReservationRepo.acceptTable(ReservationStatus.ACCEPTED, tableId, userId);
//    	return true;
//    }
//
//    @Override
//    public boolean declineTable(int tableId, int userId) {
////        return tableReservationRepo.declineTable(ReservationStatus.DECLINED, tableId, userId);
//    	return true;
//    }

//	@Override
//	public void updateReservation(TableReservation reservation) {
//		// TODO Auto-generated method stub
//		
//	}

	

	
    
    
}


