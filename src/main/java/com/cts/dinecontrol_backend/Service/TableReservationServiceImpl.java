package com.cts.dinecontrol_backend.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dinecontrol_backend.Repository.TableReservationRepo;
import com.cts.dinecontrol_backend.Repository.TableTypeRepo;
import com.cts.dinecontrol_backend.Repository.UserRepo;
import com.cts.dinecontrol_backend.dtolayer.TableReservationDTO;
import com.cts.dinecontrol_backend.models.ReservationStatus;
import com.cts.dinecontrol_backend.models.TableReservation;
import com.cts.dinecontrol_backend.models.TableType;
import com.cts.dinecontrol_backend.models.User;

@Service
public class TableReservationServiceImpl implements TableReservationService {
    @Autowired
    private TableReservationRepo tableReservationRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private TableTypeRepo tableTypeRepo;

    @Override
    public List<TableReservation> getAllReservations() {
        return tableReservationRepo.findAll();
    }

    @Override
    public List<TableReservation> getReservationsByUserId(int userId) {
//        return tableReservationRepo.findByUserId(userId);
    	return null;
    }

    public boolean makeReservation(TableReservationDTO tableReservationDTO) {
        try {
        	Optional<User> user = userRepo.findById(tableReservationDTO.userId());
        	Optional<TableType> table = tableTypeRepo.findById(tableReservationDTO.tableId());
            TableReservation tableReservation = TableReservation.builder()
            		.reservationDate(tableReservationDTO.reservationDate())
            		.reservationTime(tableReservationDTO.reservationTime())
            		.userName(tableReservationDTO.userName())
            		.status(ReservationStatus.PENDING)
            		.table(table.get())
            		.user(user.get())
            		.build();
        	tableReservationRepo.save(tableReservation);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to make reservation: " + e.getMessage());
            return false;
        }
    }


    public TableReservation createReservation(TableReservation reservation) {
        reservation.setStatus(ReservationStatus.PENDING);
        return tableReservationRepo.save(reservation);
    }

    public TableReservation updateReservationStatus(int reservationId, ReservationStatus status) {
      Optional<TableReservation> reservation = tableReservationRepo.findById(reservationId);
      if(reservation.isPresent()) {
    	  
//    	  reservation.get().setStatus(status);
      } 
        return tableReservationRepo.save(reservation.get());
    }


    @Override
    public void deleteReservation(int reservationId) {
        tableReservationRepo.deleteById(reservationId);
    }

	

	@Override
	public void acceptReservation(int reservationId) {
		// TODO Auto-generated method stub
		tableReservationRepo.acceptReservation(reservationId);
		
	}

	@Override
	public void declineReservation(int reservationId) {
		tableReservationRepo.declineReservation(reservationId);
		
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


