package com.brownfield.booking.proxy;


import com.brownfield.booking.model.Fare;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "fare-service")
public interface FareServiceProxy {

    @RequestMapping(value = "/get", method = GET)
    Fare getFare(@RequestParam(value = "flightNumber") String flightNumber, @RequestParam(value = "flightDate") String flightDate);
}
