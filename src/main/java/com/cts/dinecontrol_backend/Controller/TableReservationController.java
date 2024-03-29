package com.cts.dinecontrol_backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.dinecontrol_backend.Service.TableReservationService;
import com.cts.dinecontrol_backend.dtolayer.TableReservationDTO;
import com.cts.dinecontrol_backend.models.ReservationStatus;
import com.cts.dinecontrol_backend.models.TableReservation;

import java.util.List;

@RestController
@RequestMapping("/table-reservations")
public class TableReservationController {

    @Autowired
    private TableReservationService tableReservationService;

    @PostMapping("/request")
    public ResponseEntity<String> makeReservationRequest(@RequestBody TableReservationDTO tableReservationDTO) {
         tableReservationService.makeReservation(tableReservationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Reservation request sent.");
    }

    @GetMapping("/allrequests")
    public ResponseEntity<List<TableReservation>> getAllReservationRequests() {
        List<TableReservation> reservationRequests = tableReservationService.getAllReservations();
        return ResponseEntity.ok(reservationRequests);
    }


    @PutMapping("/accept")
    public ResponseEntity<String> acceptReservation(@RequestParam("reservation_id") int reservationId) {
        tableReservationService.acceptReservation(reservationId);
        return ResponseEntity.ok("Table request Accepted");
    }

    @PutMapping("/decline")
    public ResponseEntity<String> declineReservation(@RequestParam("reservation_id") int reservationId) {
      tableReservationService.declineReservation(reservationId);
        return ResponseEntity.ok("Table request Declined");
    }
}

