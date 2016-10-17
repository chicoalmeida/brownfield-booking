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
    public ResponseEntity<Long> book(@RequestBody BookingRecord bookingRecord) {

        ResponseEntity<Long> responseEntity;
        Optional<Long> book;
        try {
            book = bookingService.book(bookingRecord);
            if (book.isPresent())
                responseEntity = new ResponseEntity<>(book.get(), HttpStatus.OK);
            else
                responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FareException | InventoryException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return responseEntity;
    }
}
