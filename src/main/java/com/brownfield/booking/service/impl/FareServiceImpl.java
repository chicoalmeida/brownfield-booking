package com.brownfield.booking.service.impl;

import com.brownfield.booking.entity.BookingRecord;
import com.brownfield.booking.exception.FareException;
import com.brownfield.booking.model.Fare;
import com.brownfield.booking.service.FareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

@EnableFeignClients
@RefreshScope
@Component
@Slf4j
public class FareServiceImpl implements FareService {


    private final FareServiceProxy fareServiceProxy;

    @Autowired
    public FareServiceImpl(final FareServiceProxy fareServiceProxy) {
        this.fareServiceProxy = fareServiceProxy;
    }

    @Override
    public void validateFare(final BookingRecord bookingRecord) throws FareException {
        log.info("calling fares to get fare");

        Fare fare = fareServiceProxy.getFare(bookingRecord.getFlightNumber(), bookingRecord.getFlightDate());

        log.info("calling fares to get fare {}", fare);

        if (!bookingRecord.getFare().equals(fare.getFare())) {
            throw new FareException("fare is tampered");
        }
    }
}
