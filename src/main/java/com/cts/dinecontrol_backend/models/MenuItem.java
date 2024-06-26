package com.cts.dinecontrol_backend.models;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private String groupName;

    private String name;

    private BigDecimal price;

    private String taste;

    private String photoUrl;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "manager_id")
    private Manager manager;

	
}
