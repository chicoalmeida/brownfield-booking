package com.brownfield.booking.service.impl;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.BookingException;
import com.brownfield.booking.repository.BookingRepository;
import com.brownfield.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Optional<Long> book(final BookingRecord bookingRecord) throws BookingException {

        ensureFareConsistency(bookingRecord);

        return Optional.of(101010L);
    }

    private void ensureFareConsistency(final BookingRecord bookingRecord) throws BookingException {

    }
}
