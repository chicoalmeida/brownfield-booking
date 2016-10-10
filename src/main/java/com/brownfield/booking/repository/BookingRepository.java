package com.brownfield.booking.repository;


import com.brownfield.booking.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {

}
