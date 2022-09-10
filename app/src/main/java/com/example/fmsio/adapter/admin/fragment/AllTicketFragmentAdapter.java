package com.example.fmsio.adapter.admin.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fmsio.R;
import com.example.fmsio.adapterCallBack.admin.fragment.AllTicketAdapterCallBack;
import com.example.fmsio.databinding.CardAdminTicketStatusBinding;
import com.example.fmsio.databinding.TicketDesignAllTicketDashboardBinding;
import com.example.fmsio.databinding.TicketDesignAllTicketDashboardBindingImpl;
import com.example.fmsio.model.Ticket;

import java.util.ArrayList;

public class AllTicketFragmentAdapter  extends RecyclerView.Adapter<AllTicketFragmentAdapter.Holder>{
    private AllTicketAdapterCallBack listener;
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
    public AllTicketFragmentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TicketDesignAllTicketDashboardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.ticket_design_all_ticket_dashboard, parent,false);
        return new AllTicketFragmentAdapter.Holder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull AllTicketFragmentAdapter.Holder holder, int position) {
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
        final private TicketDesignAllTicketDashboardBinding binding;

        Holder(@NonNull TicketDesignAllTicketDashboardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
