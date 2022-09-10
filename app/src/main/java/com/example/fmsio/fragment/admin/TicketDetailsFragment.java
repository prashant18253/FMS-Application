package com.example.fmsio.fragment.admin;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fmsio.R;
import com.example.fmsio.databinding.FragmentAdminAllTicketsDashboardBinding;
import com.example.fmsio.databinding.FragmentTicketDetailsBinding;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.model.User;
import com.example.fmsio.viewModel.admin.AdminMainViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TicketDetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener, QueryCallBack {
    private FragmentTicketDetailsBinding binding;
    private Ticket ticket;
    private AdminMainViewModel viewModel;
    private FirebaseFirestore db;
    private String uid;
    private String[] employees=null;
    String s=null;
    private final String[] priorities = { "Low", "Medium", "High"};
    public TicketDetailsFragment(Ticket ticket) {
        this.ticket = ticket;
    }

    public static TicketDetailsFragment newInstance(Ticket ticket) {
        TicketDetailsFragment fragment = new TicketDetailsFragment(ticket);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_ticket_details, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AdminMainViewModel.class);

        LiveData<ArrayList<User>> allEmployeesLiveData = viewModel.getAllEmployees(this);

        final Observer<ArrayList<User>> observer = users -> {

            employees = new String[users.size()];
            for(int i =0;i<users.size();i++)
                employees[i] = users.get(i).getName();
            s = String.valueOf(ticket.getAssignedTo());
            binding.employeeText.setText(s);
            if(s==null || s.length()==0) {
                binding.priority.setOnItemSelectedListener(this);
                ArrayAdapter assignAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, employees);
                assignAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.assign.setAdapter(assignAdapter);
            }
        };
        allEmployeesLiveData.observe(requireActivity(), observer);

        String val = String.valueOf(ticket.getPriority());
        binding.priorityText.setText(val);
        binding.serviceType.setText(ticket.getName());
        binding.hostelName.setText(ticket.getHostelName());
        binding.floorNumber.setText(String.valueOf(ticket.getFloorNo()));
        binding.roomNumber.setText(String.valueOf(ticket.getRoomNo()));
        binding.priority.setOnItemSelectedListener(this);
        ArrayAdapter priorityAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, priorities);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.priority.setAdapter(priorityAdapter);

        Log.i("Adafsfsfs",String.valueOf(binding.employeeText.getText()));
        if(ticket.getAssignedTo()!=null && ticket.getAssignedTo().length()>0)
            binding.submit.setEnabled(false);

        binding.submit.setOnClickListener(view -> {
            Map<String, Object> data = new HashMap<>();
            if(binding.priority.getSelectedItem().toString().length()!=0 ||  binding.assign.getSelectedItem().toString().length()!=0)
            {
                db = FirebaseFirestore.getInstance();
                data.put("priority", binding.priority.getSelectedItem().toString().toLowerCase());
                data.put("assignedTo", binding.assign.getSelectedItem().toString());
                data.put("status", "assigned");
                db.collection("tickets").document(ticket.getId()).set(data, SetOptions.merge());
            }
            Toast.makeText(getContext(), "Successfully assigned", Toast.LENGTH_SHORT).show();
            //db
            // = FirebaseFirestore.getInstance();

        });
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