package com.cts.dinecontrol_backend.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="table_reservation")
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableType table;

    private LocalDate reservationDate;

    private LocalTime reservationTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}



