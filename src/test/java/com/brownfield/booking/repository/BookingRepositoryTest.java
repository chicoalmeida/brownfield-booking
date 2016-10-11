package com.brownfield.booking.repository;

import com.brownfield.booking.entity.BookingRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Slf4j
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Before
    public void setUp() {
        Optional<List<BookingRecord>> records = Optional.ofNullable(bookingRepository.findAll());
        records.ifPresent(r -> this.bookingRepository.delete(r));
    }

    @Test
    public void save_ShouldCreateValidBookingObject() {


        BookingRecord result = bookingRepository.save(createBookingRecordFixture());

        log.info(result.toString());

        assertNotNull(result);
        assertThat(result.getFlightNumber(), equalTo("0001"));

    }


    private BookingRecord createBookingRecordFixture() {
        return BookingRecord.createBookingRecord("0001", "GUARULHOS", "GALEAO", "16-10/2016", LocalDateTime.now(), "17.90", "AVAILABLE");
    }

}