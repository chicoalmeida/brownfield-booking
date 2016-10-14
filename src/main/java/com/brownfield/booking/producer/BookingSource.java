package com.brownfield.booking.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface BookingSource {
    String InventoryQ = "inventoryQ";

    @Output("inventoryQ")
    MessageChannel inventoryQ();

}