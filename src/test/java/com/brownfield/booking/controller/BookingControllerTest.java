package com.brownfield.booking.controller;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.BookingException;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public void book_ShouldReturnTheCodeOfBookedTicket() throws BookingException, FareException {

        BookingRecord bookingRecordFixture = createBookingRecordFixture();

        Mockito.when(bookingService.book(bookingRecordFixture)).thenReturn(Optional.of(101010L));

        ResponseEntity<Long> result = target.book(bookingRecordFixture);


        assertNotNull(result);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));

        assertThat(result.getBody(), equalTo(101010L));
    }

    private BookingRecord createBookingRecordFixture() {
        return BookingRecord.builder()
                .flightNumber("0001")
                .flightDate("16-10-2016")
                .origin("GUARULHOS")
                .destination("GALEAO")
                .fare("17.90")
                .build();
    }


}