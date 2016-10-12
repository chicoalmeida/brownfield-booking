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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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

        Mockito.when(bookingService.book(bookingRecordFixture)).thenReturn(Optional.of(101010L));

        ResponseEntity<Long> result = target.book(bookingRecordFixture);


        assertNotNull(result);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));

        assertThat(result.getBody(), equalTo(101010L));
    }

    private BookingRecord createBookingRecordFixture() {
        return BookingRecord.createBookingRecord("0001", "GUARULHOS", "GALEAO", "16-10/2016", LocalDateTime.now(), "17.90", "AVAILABLE");
    }


}