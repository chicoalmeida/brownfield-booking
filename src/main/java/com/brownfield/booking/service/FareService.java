package com.brownfield.booking.service;


import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.FareException;

public interface FareService {

    void validateFare(BookingRecord bookingRecord) throws FareException;
}
