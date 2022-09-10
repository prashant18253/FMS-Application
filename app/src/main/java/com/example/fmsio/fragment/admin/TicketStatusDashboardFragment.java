package com.example.fmsio.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fmsio.R;
import com.example.fmsio.adapter.admin.fragment.TicketStatusFragmentAdapter;
import com.example.fmsio.adapterCallBack.admin.fragment.TicketStatusAdapterCallBack;
import com.example.fmsio.databinding.FragmentAdminTicketStatusDashboardBinding;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.viewModel.admin.AdminMainViewModel;

import java.util.ArrayList;

public class TicketStatusDashboardFragment extends Fragment implements TicketStatusAdapterCallBack, QueryCallBack {
    private FragmentAdminTicketStatusDashboardBinding binding;
    private AdminMainViewModel viewModel;
    private TicketStatusFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_admin_ticket_status_dashboard, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AdminMainViewModel.class);

        adapter = new TicketStatusFragmentAdapter(this);
        binding.rvTicketStatus.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.rvTicketStatus.setAdapter(adapter);

        LiveData<ArrayList<Ticket>> ticketsLiveData = viewModel.getNonClosedTickets(this);
        final Observer<ArrayList<Ticket>> observer = tickets -> adapter.setTickets(tickets);
        ticketsLiveData.observe(requireActivity(), observer);
        return binding.getRoot();
    }


    @Override
    public void onItemClick(Ticket ticket) {
        Toast.makeText(requireActivity(), "You clicked on the item bitch", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQueryReadError() {

    }

    @Override
    public void onQueryEmpty() {

    }

    @Override
    public void onQueryExistent() {

    }
}
