package com.example.ticketing.service;

import javax.inject.Inject;

import com.example.ticketing.common.HttpClient;

import com.example.ticketing.proto.TicketOuterClass.Ticket;

public class TicketService {
    private final HttpClient httpClient;

    @Inject
    public TicketService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Ticket create(String productName, Integer price) {
        return httpClient.post(productName, price);
    }
}
