package com.brownfield.booking.service.impl;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.entity.Inventory;
import com.brownfield.booking.exception.InventoryException;
import com.brownfield.booking.repository.InventoryRepository;
import com.brownfield.booking.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public int updateInventory(final BookingRecord bookingRecord) throws InventoryException {
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(bookingRecord.getFlightNumber(), bookingRecord.getFlightDate());
        if (!inventory.isAvailable(bookingRecord.getPassengers().size())) {
            throw new InventoryException("No more seats available");
        }
        log.info("successfully checked inventory" + inventory);
        log.info("calling inventory to update inventory");
        //update inventory
        inventory.setAvailable(inventory.getAvailable() - bookingRecord.getPassengers().size());
        inventoryRepository.saveAndFlush(inventory);
        log.info("successfully updated inventory");
        return inventory.getBookableInventory();
    }
}
