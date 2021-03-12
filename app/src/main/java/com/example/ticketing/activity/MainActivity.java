package com.example.ticketing.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketing.R;
import com.example.ticketing.TicketingApplication;
import com.example.ticketing.adapter.TicketAdapter;
import com.example.ticketing.proto.TicketOuterClass.Ticket;
import com.example.ticketing.service.TicketService;
import com.example.ticketing.util.EditTextUtil;
import com.example.ticketing.util.IntegerUtil;
import com.example.ticketing.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.ticketing.common.Constants.ERROR_FILL_INPUTS;
import static com.example.ticketing.common.Constants.ERROR_PRICE_NEGATIVE;
import static com.example.ticketing.common.Constants.ERROR_PRICE_TEXT;
import static com.example.ticketing.common.Constants.STRING_EMPTY;

public class MainActivity extends BaseActivity {
    private final List<Ticket> tickets = new ArrayList<>();
    private TicketAdapter ticketAdapter;

    @Inject
    TicketService ticketService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TicketingApplication) getApplication()).getComponent().inject(this);
        initAdapter();
    }

    public void createTicket(View view) {
        String message;
        String productName = findInputText(R.id.product_name);
        String productPrice = findInputText(R.id.product_price);
        Integer price = IntegerUtil.parse(productPrice);

        if (productName.isEmpty() || productPrice.isEmpty()) {
            message = ERROR_FILL_INPUTS;
        } else if (price == null) {
            message = ERROR_PRICE_TEXT;
        } else if (price < 0) {
            message = ERROR_PRICE_NEGATIVE;
        } else {
            Ticket ticket = ticketService.create(productName, price);
            tickets.add(ticket);
            ticketAdapter.notifyDataSetChanged();
            findView(R.id.list_empty).setVisibility(View.INVISIBLE);
            clearInput(R.id.product_name);
            clearInput(R.id.product_price);
            return;
        }
        ToastUtil.show(getApplicationContext(), message, Toast.LENGTH_LONG);
    }

    private void initAdapter() {
        ticketAdapter = new TicketAdapter(getApplicationContext(), tickets);
        RecyclerView recyclerView = (RecyclerView) findView(R.id.list_tickets);
        recyclerView.setAdapter(ticketAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private String findInputText(int id) {
        EditText editText = (EditText) findView(id);
        return EditTextUtil.findText(editText);
    }

    private void clearInput(int id) {
        EditText editText = (EditText) findView(id);
        EditTextUtil.clear(editText);
    }
}