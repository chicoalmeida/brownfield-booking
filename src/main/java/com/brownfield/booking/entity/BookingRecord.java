package com.brownfield.booking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class BookingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String flightDate;
    private LocalDateTime bookingDate;
    private String fare;
    private String status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bookingRecord")
    private Set<Passenger> passengers;


    private BookingRecord(final String flightNumber, final String origin, final String destination, final String flightDate, final LocalDateTime bookingDate, final String fare, final String status) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.bookingDate = bookingDate;
        this.fare = fare;
        this.status = status;
    }

    public static BookingRecord createBookingRecord(final String flightNumber, final String origin, final String destination, final String flightDate, final LocalDateTime bookingDate, final String fare, final String status) {
        return new BookingRecord(flightNumber, origin, destination, flightDate, bookingDate, fare, status);
    }
}



