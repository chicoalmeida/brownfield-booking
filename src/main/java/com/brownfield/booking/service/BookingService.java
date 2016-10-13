package com.brownfield.booking.service;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.FareException;

import java.util.Optional;

public interface BookingService {

    Optional<Long> book(BookingRecord bookingRecord) throws FareException;
}
