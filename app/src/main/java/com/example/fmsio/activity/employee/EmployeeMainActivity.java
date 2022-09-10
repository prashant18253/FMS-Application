package com.example.fmsio.activity.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.fmsio.R;
import com.example.fmsio.databinding.ActivityEmployeeMainBinding;
import com.example.fmsio.fragment.employee.AllTicketsDashboardFragment;
import com.example.fmsio.fragment.employee.HomeDashboardFragment;
import com.example.fmsio.fragment.employee.ProfileDashboardFragment;
import com.example.fmsio.viewModel.employee.EmployeeMainViewModel;

import java.util.Objects;

public class EmployeeMainActivity extends AppCompatActivity {

    private ActivityEmployeeMainBinding binding;
    private EmployeeMainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_main);
        viewModel = new ViewModelProvider(this).get(EmployeeMainViewModel.class);

        binding.bottomNavAdmin.setSelected(0, true);
        fragmentHandler(4);

        binding.bottomNavAdmin.addBubbleListener(i -> {
            if(i == R.id.home) fragmentHandler(0);
            if(i == R.id.allTickets) fragmentHandler(1);
//            if(i == R.id.ticketStatus) fragmentHandler(3);
            if(i == R.id.profile) fragmentHandler(4);
        });
    }
    private void fragmentHandler(int itemIndex) {
        Fragment selectedFragment = null;
        switch (itemIndex){
            case 0:
                selectedFragment = new HomeDashboardFragment();
                break;
            case 1:
                selectedFragment = new AllTicketsDashboardFragment();
                break;
            case 4:
                selectedFragment = new ProfileDashboardFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_holder, Objects.requireNonNull(selectedFragment)).commit();
    }
}