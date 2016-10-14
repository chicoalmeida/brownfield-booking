package com.brownfield.booking.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@RefreshScope
@Component
@EnableBinding(BookingSource.class)
public class Sender {

    @Output(BookingSource.InventoryQ)
    @Autowired
    private MessageChannel messageChannel;


    public Sender() {

    }

    public void send(Object message) {
        messageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
