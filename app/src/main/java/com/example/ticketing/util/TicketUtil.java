package com.example.ticketing.util;

import com.example.ticketing.proto.TicketOuterClass.Ticket;

public class TicketUtil {
    private static int id = 1;

    public static Ticket create(String productName, Integer price) {
        return Ticket.newBuilder()
                .setId(id++)
                .setProductName(productName)
                .setPrice(price)
                .build();
    }
}
