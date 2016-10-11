package com.brownfield.booking.controller;

import com.brownfield.booking.entity.BookingRecord;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(BookingController.BOOKING_MAPPING)
public class BookingController {

    public static final String BOOKING_MAPPING = "/booking";

    public Long book(final BookingRecord bookingRecordFixture) {

        return 1L;
    }
}
