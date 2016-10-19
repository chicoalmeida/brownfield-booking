package com.brownfield.booking.service;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.exception.InventoryException;

import java.util.Optional;

public interface BookingService {

    Optional<Long> book(BookingRecord bookingRecord) throws FareException, InventoryException;
    void updateStatus(String status, long bookingId);

    BookingRecord getBooking(long id);
}
