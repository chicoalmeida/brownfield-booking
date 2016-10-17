package com.brownfield.booking.consumer;


import com.brownfield.booking.model.BookingStatus;
import com.brownfield.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

interface BookingSink {
    String CHECKINQ = "checkInQ";

    @Input("checkInQ")
    MessageChannel checkInQ();

}

@EnableBinding(BookingSink.class)
@Component
public class Receiver {


    @Autowired
    BookingService bookingComponent;

    public Receiver() {

    }

    /**
     * public Receiver(BookingComponent bookingComponent){
     * this.bookingComponent = bookingComponent;
     * }
     *
     * @RabbitListener(queues = "CheckInQ")
     * public void processMessage(long bookingID ) {
     * bookingComponent.updateStatus(BookingStatus.CHECKED_IN, bookingID);
     * }
     **/
    @ServiceActivator(inputChannel = BookingSink.CHECKINQ)
    public void accept(long bookingID) {
        bookingComponent.updateStatus(BookingStatus.CHECKED_IN, bookingID);
    }
}