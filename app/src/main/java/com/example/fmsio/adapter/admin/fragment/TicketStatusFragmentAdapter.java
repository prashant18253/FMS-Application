package com.example.fmsio.adapter.admin.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fmsio.R;
import com.example.fmsio.adapterCallBack.admin.fragment.TicketStatusAdapterCallBack;
import com.example.fmsio.databinding.CardAdminTicketStatusBinding;
import com.example.fmsio.model.Ticket;

import java.util.ArrayList;

public class TicketStatusFragmentAdapter extends RecyclerView.Adapter<TicketStatusFragmentAdapter.Holder> {
    private TicketStatusAdapterCallBack listener;
    private ArrayList<Ticket> tickets;

    public TicketStatusFragmentAdapter(TicketStatusAdapterCallBack listener) {
        this.listener = listener;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardAdminTicketStatusBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.card_admin_ticket_status, parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.binding.setTicket(ticket);
        holder.binding.setContract(listener);
    }

    @Override
    public int getItemCount() {
        if(tickets != null){
            return tickets.size();
        }else{
            return 0;
        }
    }

    static class Holder extends RecyclerView.ViewHolder{
        final private CardAdminTicketStatusBinding binding;

        Holder(@NonNull CardAdminTicketStatusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
