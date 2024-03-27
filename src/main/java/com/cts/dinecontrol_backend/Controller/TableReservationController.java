package com.cts.dinecontrol_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.dinecontrol_backend.Service.TableReservationService;
import com.cts.dinecontrol_backend.models.ReservationStatus;
import com.cts.dinecontrol_backend.models.TableReservation;

import java.util.List;

@RestController
@RequestMapping("/table-reservations")
public class TableReservationController {

    @Autowired
    private TableReservationService tableReservationService;

    @PostMapping("/request")
    public ResponseEntity<Void> makeReservationRequest(@RequestBody TableReservation reservation) {
        boolean success = tableReservationService.makeReservation(reservation);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/requests")
    public ResponseEntity<List<TableReservation>> getAllReservationRequests() {
        List<TableReservation> reservationRequests = tableReservationService.getAllReservations();
        return ResponseEntity.ok(reservationRequests);
    }

//    @PutMapping("/accept/{reservationId}")
//    public ResponseEntity<Void> acceptReservation(@PathVariable int reservationId) {
//        boolean success = tableReservationService.confirmTable(reservationId);
//        if (success) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PutMapping("/decline/{reservationId}")
//    public ResponseEntity<Void> declineReservation(@PathVariable int reservationId) {
//        boolean success = tableReservationService.confirmTable1(reservationId);
//        if (success) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//    @PutMapping("/{reservationId}/accept")
//    public ResponseEntity<TableReservation> acceptReservation(@PathVariable Long reservationId) {
//        TableReservation updatedReservation = tableReservationService.updateReservationStatus(reservationId, ReservationStatus.ACCEPTED);
//        return ResponseEntity.ok(updatedReservation);
//    }
//
//    @PutMapping("/{reservationId}/decline")
//    public ResponseEntity<TableReservation> declineReservation(@PathVariable Long reservationId) {
//        TableReservation updatedReservation = tableReservationService.updateReservationStatus(reservationId, ReservationStatus.DECLINED);
//        return ResponseEntity.ok(updatedReservation);
//    }
}

