package com.brownfield.booking;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.entity.Inventory;
import com.brownfield.booking.entity.Passenger;
import com.brownfield.booking.repository.InventoryRepository;
import com.brownfield.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@Slf4j
public class BrownFieldBookingApplication implements CommandLineRunner {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private InventoryRepository inventoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(BrownFieldBookingApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        List<Inventory> inventories = asList(Inventory.builder().flightDate("BF100").flightNumber("22-JAN-16").available(100).build(),
                Inventory.builder().flightDate("BF101").flightNumber("22-JAN-16").available(100).build(),
                Inventory.builder().flightDate("BF102").flightNumber("22-JAN-16").available(100).build(),
                Inventory.builder().flightDate("BF103").flightNumber("22-JAN-16").available(100).build(),
                Inventory.builder().flightDate("BF104").flightNumber("22-JAN-16").available(100).build(),
                Inventory.builder().flightDate("BF105").flightNumber("22-JAN-16").available(100).build(),
                Inventory.builder().flightDate("BF106").flightNumber("22-JAN-16").available(100).build());

        inventoryRepository.save(inventories);

        BookingRecord booking = BookingRecord.builder()
                .flightNumber("BF101")
                .flightDate("22-JAN-16")
                .origin("NYC")
                .destination("SFO")
                .bookingDate(LocalDateTime.now())
                .fare("101")
                .build();

        Set<Passenger> passengers = new HashSet<>();
        passengers.add(new Passenger(null, "Chico", "Almeida", "Male", booking));
        booking.setPassengers(passengers);
        bookingService.book(booking).ifPresent(b -> {
            log.info("Booking successfully saved {}", b);
            log.info("Looking to load booking record...");
            log.info("Result: {}", bookingService.getBooking(b));
        });

    }
}
