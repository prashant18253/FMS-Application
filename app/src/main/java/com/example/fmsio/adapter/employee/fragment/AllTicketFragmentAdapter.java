package com.example.fmsio.adapter.employee.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.example.fmsio.R;
import com.example.fmsio.adapterCallBack.employee.fragment.AllTicketAdapterCallBack;
import com.example.fmsio.databinding.TicketDesignAllTicketDashboardEmployeeBinding;
import com.example.fmsio.model.Ticket;

import java.util.ArrayList;

public class AllTicketFragmentAdapter extends RecyclerView.Adapter<com.example.fmsio.adapter.employee.fragment.AllTicketFragmentAdapter.Holder>{
    private com.example.fmsio.adapterCallBack.employee.fragment.AllTicketAdapterCallBack listener;
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
    public com.example.fmsio.adapter.employee.fragment.AllTicketFragmentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TicketDesignAllTicketDashboardEmployeeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.ticket_design_all_ticket_dashboard_employee, parent,false);
        return new com.example.fmsio.adapter.employee.fragment.AllTicketFragmentAdapter.Holder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull com.example.fmsio.adapter.employee.fragment.AllTicketFragmentAdapter.Holder holder, int position) {
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
        final private TicketDesignAllTicketDashboardEmployeeBinding binding;

        Holder(@NonNull TicketDesignAllTicketDashboardEmployeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

