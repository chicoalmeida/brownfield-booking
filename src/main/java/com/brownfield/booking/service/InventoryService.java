package com.brownfield.booking.service;


import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.InventoryException;

public interface InventoryService {

    void updateInventory(BookingRecord bookingRecord) throws InventoryException;

}
