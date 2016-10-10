package com.brownfield.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger {

    private static final String BOOKING_JOIN_COLUMN = "BOOKING_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Passenger.BOOKING_JOIN_COLUMN)
    private BookingRecord bookingRecord;
}
