package com.example.fmsio.adapter.user.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.example.fmsio.R;
import com.example.fmsio.adapterCallBack.user.fragment.AllTicketAdapterCallBack;
import com.example.fmsio.databinding.TicketDesignAllTicketDashboardUserBinding;
import com.example.fmsio.model.Ticket;

import java.util.ArrayList;

public class AllTicketFragmentAdapter extends RecyclerView.Adapter<AllTicketFragmentAdapter.Holder>{
    private com.example.fmsio.adapterCallBack.user.fragment.AllTicketAdapterCallBack listener;
    private ArrayList<Ticket> tickets;

    public AllTicketFragmentAdapter(AllTicketAdapterCallBack listener) {
        this.listener = listener;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.fmsio.adapter.user.fragment.AllTicketFragmentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TicketDesignAllTicketDashboardUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.ticket_design_all_ticket_dashboard_user, parent,false);
        return new com.example.fmsio.adapter.user.fragment.AllTicketFragmentAdapter.Holder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull com.example.fmsio.adapter.user.fragment.AllTicketFragmentAdapter.Holder holder, int position) {
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
        final private TicketDesignAllTicketDashboardUserBinding binding;

        Holder(@NonNull TicketDesignAllTicketDashboardUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
