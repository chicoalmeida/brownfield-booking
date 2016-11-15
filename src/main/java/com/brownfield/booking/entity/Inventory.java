package com.brownfield.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FLIGHTNUMBER")
    private String flightNumber;
    @Column(name = "FLIGHTDATE")
    private String flightDate;
    @Column(name = "AVAILABLE")
    private int available;


    public boolean isAvailable(int count) {
        return ((available - count) > 5);
    }

    public int getBookableInventory() {
        return available - 5;
    }
}
