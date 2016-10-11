package com.brownfield.booking.service;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.BookingException;

import java.util.Optional;

public interface BookingService {

    Optional<Long> book(BookingRecord bookingRecord) throws BookingException;
}
