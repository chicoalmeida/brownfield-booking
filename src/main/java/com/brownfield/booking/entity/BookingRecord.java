package com.brownfield.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  BookingRecord {

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

    public BookingRecord(String flightNumber, String from, String to,
                         String flightDate, LocalDateTime bookingDate, String fare) {
        this.flightNumber = flightNumber;
        this.origin = from;
        this.destination = to;
        this.flightDate = flightDate;
        this.bookingDate = bookingDate;
        this.fare = fare;

    }
}



