package com.example.fmsio.activity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.fmsio.R;
import com.example.fmsio.databinding.ActivityUserMainBinding;
import com.example.fmsio.fragment.user.AllTicketsDashboardFragment;
import com.example.fmsio.fragment.user.HomeDashboardFragment;
import com.example.fmsio.fragment.user.ProfileDashboardFragment;
import com.example.fmsio.fragment.user.RaiseTicketDashboardFragment;
import com.example.fmsio.fragment.user.TicketStatusDashboardFragment;
import com.example.fmsio.viewModel.user.UserMainViewModel;

import java.util.Objects;

public class UserMainActivity extends AppCompatActivity {
    private ActivityUserMainBinding binding;
    private UserMainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_main);
        viewModel = new ViewModelProvider(this).get(UserMainViewModel.class);

        binding.bottomNavAdmin.setSelected(0, true);
        fragmentHandler(0);

        binding.bottomNavAdmin.addBubbleListener(i -> {
            if(i == R.id.home) fragmentHandler(0);
            if(i == R.id.allTickets) fragmentHandler(1);
            if(i == R.id.raiseTicket) fragmentHandler(2);
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
            case 2:
                selectedFragment = new RaiseTicketDashboardFragment();
                break;
            case 3:
                selectedFragment = new TicketStatusDashboardFragment();
                break;
            case 4:
                selectedFragment = new ProfileDashboardFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_holder, Objects.requireNonNull(selectedFragment)).commit();
    }
}