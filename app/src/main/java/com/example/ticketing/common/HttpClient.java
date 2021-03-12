package com.example.ticketing.common;

import com.example.ticketing.proto.TicketOuterClass.Ticket;
import com.example.ticketing.util.LogUtil;
import com.example.ticketing.util.TicketUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import static com.example.ticketing.common.Constants.CONTENT_TYPE_PROTOCOL_BUFFER;
import static com.example.ticketing.common.Constants.ERROR_IGNORE;
import static com.example.ticketing.common.Constants.ERROR_MALFORMED_URL;
import static com.example.ticketing.common.Constants.ERROR_OPEN_CONNECTION;
import static com.example.ticketing.common.Constants.HEADER_CONTENT_TYPE;
import static com.example.ticketing.common.Constants.URL_API;

public class HttpClient {
    private final String TAG = this.getClass().getName();

    @Inject
    public HttpClient() {}

    public Ticket post(String productName, Integer price) {
        Ticket ticket = TicketUtil.create(productName, price);

        try {
            URL url = new URL(URL_API);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty(HEADER_CONTENT_TYPE, CONTENT_TYPE_PROTOCOL_BUFFER);
            connection.setDoOutput(true);
            OutputStream output = connection.getOutputStream();
            ticket.writeTo(output);
            output.flush();
            output.close();
            return Ticket.parseFrom(connection.getInputStream());
        } catch (MalformedURLException e) {
            LogUtil.error(TAG, ERROR_MALFORMED_URL);
        } catch (IOException e) {
            LogUtil.error(TAG, ERROR_OPEN_CONNECTION);
        }
        LogUtil.error(TAG, ERROR_IGNORE);
        return ticket;
    }
}
