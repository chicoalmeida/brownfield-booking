package com.brownfield.booking.controller;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.BookingException;
import com.brownfield.booking.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {


    @Mock
    private BookingService bookingService;

    @InjectMocks
    private
    BookingController target = new BookingController();

    @Test
    public void book_ShouldReturnTheCodeOfBookedTicket() throws BookingException {

        BookingRecord bookingRecordFixture = createBookingRecordFixture();

        Mockito.when(bookingService.book(bookingRecordFixture)).thenReturn(Optional.empty());

        Long result = target.book(bookingRecordFixture);

        assertNotNull(result);

        assertThat(result, equalTo(101010));
    }

    private BookingRecord createBookingRecordFixture() {
        return BookingRecord.createBookingRecord("0001", "GUARULHOS", "GALEAO", "16-10/2016", LocalDateTime.now(), "17.90", "AVAILABLE");
    }


}