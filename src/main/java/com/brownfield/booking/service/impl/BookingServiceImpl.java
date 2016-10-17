package com.brownfield.booking.service.impl;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.entity.Passenger;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.exception.InventoryException;
import com.brownfield.booking.model.BookingStatus;
import com.brownfield.booking.producer.Sender;
import com.brownfield.booking.repository.BookingRepository;
import com.brownfield.booking.service.BookingService;
import com.brownfield.booking.service.FareService;
import com.brownfield.booking.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@EnableFeignClients
@RefreshScope
@Component
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FareService fareService;
    private final InventoryService inventoryService;
    private final Sender sender;

    @Autowired
    public BookingServiceImpl(final BookingRepository bookingRepository, final FareService fareService, final InventoryService inventoryService, final Sender sender) {
        this.bookingRepository = bookingRepository;
        this.fareService = fareService;
        this.inventoryService = inventoryService;
        this.sender = sender;
    }


    @Override
    public Optional<Long> book(final BookingRecord bookingRecord) throws FareException, InventoryException {

        this.fareService.validateFare(bookingRecord);

        int totalSeatsAvailable = this.inventoryService.updateInventory(bookingRecord);

        Optional<Long> bookedId = this.bookValidRequest(bookingRecord);

        this.sendMessageToUpdateSearchInventory(bookingRecord, totalSeatsAvailable);

        return bookedId;
    }

    @Override
    public void updateStatus(String status, long bookingId) {
        BookingRecord record = bookingRepository.findOne(bookingId);
        if(record == null) {
            log.info("NO BOOKING FOUND, ignoring FOR BOOKING ID.." + bookingId);
        }else {
            record.setStatus(status);
        }
    }

    private Optional<Long> bookValidRequest(final BookingRecord bookingRecord) {
        bookingRecord.setStatus(BookingStatus.BOOKING_CONFIRMED);
        Set<Passenger> passengers = bookingRecord.getPassengers();
        passengers.forEach(passenger -> passenger.setBookingRecord(bookingRecord));
        bookingRecord.setBookingDate(LocalDateTime.now());
        log.info("Successfully saved booking");
        return Optional.ofNullable(bookingRepository.save(bookingRecord).getId());
    }

    private void sendMessageToUpdateSearchInventory(final BookingRecord bookingRecord, final int totalSeatsAvailable) {
        log.info("sending a booking event");
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("FLIGHT_NUMBER", bookingRecord.getFlightNumber());
        bookingDetails.put("FLIGHT_DATE", bookingRecord.getFlightDate());
        bookingDetails.put("NEW_INVENTORY", totalSeatsAvailable);
        sender.send(bookingDetails);
        log.info("booking event successfully delivered " + bookingDetails);
    }

}
