package com.example.ticketing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing.R;

import java.util.List;

import com.example.ticketing.proto.TicketOuterClass.Ticket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    private final Context context;
    private final List<Ticket> tickets;

    public TicketAdapter(Context context, List<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_item_ticket, viewGroup, false);
        return new ViewHolder(context, view);

    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.bind(ticket);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final View view;

        public ViewHolder(Context context, @NonNull View view) {
            super(view);
            this.context = context;
            this.view = view;
        }

        public void bind(Ticket ticket) {
            setName(ticket.getProductName());
            setPrice(ticket.getPrice());
        }

        private void setName(String Name) {
            TextView textView = (TextView) findView(R.id.name);
            textView.setText(Name);
        }

        private void setPrice(int price) {
            TextView textView = (TextView) findView(R.id.price);
            String pattern = context.getResources().getString(R.string.cents);
            String cents = String.format(pattern, price);
            textView.setText(cents);
        }

        private View findView(int id) {
            return view.findViewById(id);
        }
    }
}