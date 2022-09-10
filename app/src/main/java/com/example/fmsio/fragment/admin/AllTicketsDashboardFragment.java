package com.example.fmsio.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import com.example.fmsio.adapter.admin.fragment.AllTicketFragmentAdapter;
import com.example.fmsio.adapterCallBack.admin.fragment.AllTicketAdapterCallBack;
import com.example.fmsio.databinding.FragmentAdminAllTicketsDashboardBinding;
import com.example.fmsio.databinding.FragmentAdminTicketStatusDashboardBinding;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.viewModel.admin.AdminMainViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class AllTicketsDashboardFragment extends Fragment implements QueryCallBack, AllTicketAdapterCallBack{
    private FragmentAdminAllTicketsDashboardBinding binding;
    private AdminMainViewModel viewModel;
    private AllTicketFragmentAdapter adapter;
    LiveData<ArrayList<Ticket>> ticketsLiveData;
    Observer<ArrayList<Ticket>> observer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_admin_all_tickets_dashboard, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AdminMainViewModel.class);

        adapter = new AllTicketFragmentAdapter(this);
        binding.rvTicketStatus.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.rvTicketStatus.setAdapter(adapter);


        QueryCallBack callBack = this;
        binding.opened.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.opened.isChecked()){
                    binding.high.setEnabled(false);
                    binding.low.setEnabled(false);
                    binding.medium.setEnabled(false);
                    binding.closed.setEnabled(false);
                    binding.assigned.setEnabled(false);
                    ticketsLiveData = viewModel.getOpenedTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);
                }
                else
                {
                    binding.high.setEnabled(true);
                    binding.low.setEnabled(true);
                    binding.medium.setEnabled(true);
                    binding.closed.setEnabled(true);
                    binding.assigned.setEnabled(true);
                    binding.opened.setEnabled(true);
                    ticketsLiveData = viewModel.getAllTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);


                }
            }
        });

        binding.closed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.closed.isChecked()){
                    binding.high.setEnabled(false);
                    binding.low.setEnabled(false);
                    binding.medium.setEnabled(false);
                    binding.opened.setEnabled(false);
                    binding.assigned.setEnabled(false);
                    ticketsLiveData = viewModel.getClosedTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);
                }
                else
                {
                    binding.high.setEnabled(true);
                    binding.low.setEnabled(true);
                    binding.medium.setEnabled(true);
                    binding.closed.setEnabled(true);
                    binding.assigned.setEnabled(true);
                    binding.opened.setEnabled(true);
                    ticketsLiveData = viewModel.getAllTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());

                    ticketsLiveData.observe(requireActivity(), observer);

                }
            }
        });

        binding.assigned.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.assigned.isChecked()){
                    binding.high.setEnabled(false);
                    binding.low.setEnabled(false);
                    binding.medium.setEnabled(false);
                    binding.opened.setEnabled(false);
                    binding.closed.setEnabled(false);
                    ticketsLiveData = viewModel.getAssignedTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());

                    ticketsLiveData.observe(requireActivity(), observer);
                }
                else
                {
                    binding.high.setEnabled(true);
                    binding.low.setEnabled(true);
                    binding.medium.setEnabled(true);
                    binding.closed.setEnabled(true);
                    binding.assigned.setEnabled(true);
                    binding.opened.setEnabled(true);
                    ticketsLiveData = viewModel.getAllTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());

                    ticketsLiveData.observe(requireActivity(), observer);

                }
            }
        });
        binding.low.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.low.isChecked()){
                    binding.high.setEnabled(false);
                    binding.assigned.setEnabled(false);
                    binding.medium.setEnabled(false);
                    binding.opened.setEnabled(false);
                    binding.closed.setEnabled(false);
                    ticketsLiveData = viewModel.getLowTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());

                    ticketsLiveData.observe(requireActivity(), observer);
                }
                else
                {
                    binding.high.setEnabled(true);
                    binding.low.setEnabled(true);
                    binding.medium.setEnabled(true);
                    binding.closed.setEnabled(true);
                    binding.assigned.setEnabled(true);
                    binding.opened.setEnabled(true);
                    ticketsLiveData = viewModel.getAllTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());

                    ticketsLiveData.observe(requireActivity(), observer);

                }
            }
        });
        binding.high.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.high.isChecked()){
                    binding.low.setEnabled(false);
                    binding.assigned.setEnabled(false);
                    binding.medium.setEnabled(false);
                    binding.opened.setEnabled(false);
                    binding.closed.setEnabled(false);
                    ticketsLiveData = viewModel.getHighTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);
                }
                else
                {
                    binding.high.setEnabled(true);
                    binding.low.setEnabled(true);
                    binding.medium.setEnabled(true);
                    binding.closed.setEnabled(true);
                    binding.assigned.setEnabled(true);
                    binding.opened.setEnabled(true);
                    ticketsLiveData = viewModel.getAllTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);

                }
            }
        });
        binding.medium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.medium.isChecked()){
                    binding.high.setEnabled(false);
                    binding.assigned.setEnabled(false);
                    binding.low.setEnabled(false);
                    binding.opened.setEnabled(false);
                    binding.closed.setEnabled(false);
                    ticketsLiveData = viewModel.getMediumTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);
                }
                else
                {
                    binding.high.setEnabled(true);
                    binding.low.setEnabled(true);
                    binding.medium.setEnabled(true);
                    binding.closed.setEnabled(true);
                    binding.assigned.setEnabled(true);
                    binding.opened.setEnabled(true);
                    ticketsLiveData = viewModel.getAllTickets(callBack);
                    observer = tickets -> adapter.setTickets(tickets);
                    observer.onChanged(ticketsLiveData.getValue());
                    ticketsLiveData.observe(requireActivity(), observer);

                }
            }
        });

        ticketsLiveData = viewModel.getAllTickets(callBack);
        Observer<ArrayList<Ticket>> observer = tickets -> adapter.setTickets(tickets);
        observer.onChanged(ticketsLiveData.getValue());
        ticketsLiveData.observe(requireActivity(), observer);

        return binding.getRoot();
    }


    @Override
    public void onItemClick(Ticket ticket) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_holder, Objects.requireNonNull(new TicketDetailsFragment(ticket))).addToBackStack("s").commit();


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
