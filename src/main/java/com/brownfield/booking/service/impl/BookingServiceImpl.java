package com.brownfield.booking.service.impl;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.entity.Inventory;
import com.brownfield.booking.exception.BookingException;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.repository.BookingRepository;
import com.brownfield.booking.repository.InventoryRepository;
import com.brownfield.booking.service.BookingService;
import com.brownfield.booking.service.FareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final InventoryRepository inventoryRepository;

    private final FareService fareService;

    @Autowired
    public BookingServiceImpl(final InventoryRepository inventoryRepository, final BookingRepository bookingRepository, final FareService fareService) {
        this.inventoryRepository = inventoryRepository;
        this.bookingRepository = bookingRepository;
        this.fareService = fareService;
    }


    @Override
    public Optional<Long> book(final BookingRecord bookingRecord) throws FareException {

        this.fareService.validateFare(bookingRecord);
        Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(bookingRecord.getFlightNumber(),bookingRecord.getFlightDate());

        return Optional.of(101010L);
    }

}
