package com.brownfield.booking.controller;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.exception.InventoryException;
import com.brownfield.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(BookingController.BOOKING_MAPPING)
public class BookingController {

    public static final String BOOKING_MAPPING = "/booking";

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> book(@RequestBody BookingRecord bookingRecord) {

        ResponseEntity<String> responseEntity;
        Optional<Long> book;
        try {
            book = bookingService.book(bookingRecord);
            responseEntity = book
                    .map(b-> new ResponseEntity<>(b.toString(), HttpStatus.OK))
                    .orElseThrow(()-> new InventoryException("No More Seats Available"));
        } catch (FareException | InventoryException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return responseEntity;
    }

    @RequestMapping("/get/{id}")
    BookingRecord getBooking(@PathVariable long id){
        return bookingService.getBooking(id);
    }
}
