package com.brownfield.booking.service;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.model.Fare;
import com.brownfield.booking.proxy.FareServiceProxy;
import com.brownfield.booking.service.impl.FareServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FareServiceImplTest {

    @Mock
    private FareServiceProxy fareServiceProxy;

    @InjectMocks
    private FareServiceImpl target = new FareServiceImpl(fareServiceProxy);

    @Test
    public void validate_GivenAValidBooking_ShouldAcceptFare() throws FareException {

        BookingRecord recordFixture = createBookingRecordFixture();
        Fare fareFixture = Fare.builder().fare("17.90").flightDate("16-10-2016").flightNumber("0001").build();

        when(fareServiceProxy.getFare(recordFixture.getFlightNumber(), recordFixture.getFlightDate()))
                .thenReturn(fareFixture);

        target.validateFare(recordFixture);

        verify(fareServiceProxy).getFare(recordFixture.getFlightNumber(), recordFixture.getFlightDate());


    }

    @Test(expected = FareException.class)
    public void validate_GivenTamperedFare_ShouldThrownFareException() throws FareException {

        BookingRecord recordFixture = createBookingRecordFixture();
        Fare fareFixture = Fare.builder().fare("17.80").flightDate("16-10-2016").flightNumber("0001").build();

        when(fareServiceProxy.getFare(recordFixture.getFlightNumber(), recordFixture.getFlightDate()))
                .thenReturn(fareFixture);

        target.validateFare(recordFixture);

        verify(fareServiceProxy).getFare(recordFixture.getFlightNumber(), recordFixture.getFlightDate());


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