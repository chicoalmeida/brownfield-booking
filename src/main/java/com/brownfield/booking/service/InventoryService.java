package com.brownfield.booking.service;


import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.InventoryException;

public interface InventoryService {

    int updateInventory(BookingRecord bookingRecord) throws InventoryException;

}
