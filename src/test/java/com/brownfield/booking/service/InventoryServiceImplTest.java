package com.brownfield.booking.service;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.entity.Inventory;
import com.brownfield.booking.entity.Passenger;
import com.brownfield.booking.exception.InventoryException;
import com.brownfield.booking.repository.InventoryRepository;
import com.brownfield.booking.service.impl.InventoryServiceImpl;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceImplTest {


    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl target = new InventoryServiceImpl();


    @Test
    public void updateInventory_ShouldDecreaseTheNumberOfAvailableSeats() throws InventoryException {

        final Integer INVENTORY_AVAILABLE_SEATS_AFTER_INTERACTION = 9;

        BookingRecord bookingRecordFixture = createBookingRecordFixture();
        Inventory inventoryFixture = createInventoryFixture();

        when(inventoryRepository.findByFlightNumberAndFlightDate(bookingRecordFixture.getFlightNumber(), bookingRecordFixture.getFlightDate()))
                .thenReturn(inventoryFixture);

        target.updateInventory(bookingRecordFixture);

        Mockito.verify(inventoryRepository, times(1)).findByFlightNumberAndFlightDate(bookingRecordFixture.getFlightNumber(), bookingRecordFixture.getFlightDate());
        Mockito.verify(inventoryRepository, times(1)).saveAndFlush(inventoryFixture);

        assertNotNull(inventoryFixture);
        assertThat(inventoryFixture.getAvailable(), equalTo(INVENTORY_AVAILABLE_SEATS_AFTER_INTERACTION));

    }

    @Test(expected = InventoryException.class)
    public void updateInventory_ShouldThrownExceptionWhenThereIsNoSeatsAvailable() throws InventoryException {

        BookingRecord bookingRecordFixture = createBookingRecordFixture();
        Inventory inventoryFixture = createInventoryFixture();
        inventoryFixture.setAvailable(0);

        when(inventoryRepository.findByFlightNumberAndFlightDate(bookingRecordFixture.getFlightNumber(), bookingRecordFixture.getFlightDate()))
                .thenReturn(inventoryFixture);

        target.updateInventory(bookingRecordFixture);

    }

    private Inventory createInventoryFixture() {
        return Inventory.builder()
                .flightNumber("0001")
                .flightDate("16-10-2016")
                .available(10)
                .build();
    }

    private BookingRecord createBookingRecordFixture() {

        BookingRecord bookingRecord = BookingRecord.builder()
                .id(1L)
                .status("TESTE")
                .flightNumber("0001")
                .flightDate("16-10-2016")
                .origin("GUARULHOS")
                .destination("GALEAO")
                .fare("17.90")
                .build();

        Set<Passenger> passengersFixture = new HashSet<>(createPassangerFixture(bookingRecord));

        bookingRecord.setPassengers(passengersFixture);

        return bookingRecord;
    }

    private List<Passenger> createPassangerFixture(final BookingRecord bookingRecordFixture) {
        return singletonList(Passenger.builder()
                .firstName("Chico")
                .lastName("Almeida")
                .gender("Male")
                .bookingRecord(bookingRecordFixture)
                .build());
    }
}