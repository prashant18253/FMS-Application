package com.example.fmsio.fragment.employee;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.fmsio.R;
import com.example.fmsio.databinding.FragmentTicketDetails3Binding;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.viewModel.employee.EmployeeMainViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class TicketDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener, QueryCallBack {
    private FragmentTicketDetails3Binding binding;
    private Ticket ticket;
    private EmployeeMainViewModel viewModel;
    private FirebaseFirestore db;
    private String uid;
    private String[] employees = null;
    String s = null;
    private final String[] priorities = {"Low", "Medium", "High"};

    public TicketDetailsFragment(Ticket ticket) {
        this.ticket = ticket;
    }

    public static com.example.fmsio.fragment.employee.TicketDetailsFragment newInstance(Ticket ticket) {
        com.example.fmsio.fragment.employee.TicketDetailsFragment fragment = new com.example.fmsio.fragment.employee.TicketDetailsFragment(ticket);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_ticket_details3, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(EmployeeMainViewModel.class);

        String val = String.valueOf(ticket.getPriority());
        binding.priorityText.setText(val);
        binding.serviceType.setText(ticket.getName());
        binding.hostelName.setText(ticket.getHostelName());
        binding.floorNumber.setText(String.valueOf(ticket.getFloorNo()));
        binding.roomNumber.setText(String.valueOf(ticket.getRoomNo()));
        binding.employeeText.setText(String.valueOf(ticket.getAssignedTo()));
        return binding.getRoot();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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