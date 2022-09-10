package com.example.fmsio.fragment.user;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmsio.R;
import com.example.fmsio.adapter.admin.fragment.HomeDashboardFragmentAdapter;
import com.example.fmsio.callBack.admin.fragment.AdminAddEmployeeCallBack;
import com.example.fmsio.databinding.FragmentAdminHomeDashboardBinding;
import com.example.fmsio.databinding.FragmentUserHomeDashboardBinding;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.fragment.admin.HomeDashboardAddEmployeeFragment;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.model.User;
import com.example.fmsio.viewModel.admin.AdminMainViewModel;
import com.example.fmsio.viewModel.user.UserMainViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Objects;

public class HomeDashboardFragment extends Fragment implements  QueryCallBack {
    private FragmentUserHomeDashboardBinding binding;
    private UserMainViewModel viewModel;
    private ProgressDialog progressDialog;
    private HomeDashboardFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_user_home_dashboard, container, false);
        //binding.setContract(this);
        viewModel = new ViewModelProvider(requireActivity()).get(UserMainViewModel.class);

        adapter = new HomeDashboardFragmentAdapter(getActivity().getApplicationContext());
        binding.rvEmployeesList.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));

        LiveData<ArrayList<User>> allEmployeesLiveData = viewModel.getAllEmployess(this);
        final Observer<ArrayList<User>> observer = users -> adapter.setUsers(users);
        allEmployeesLiveData.observe(requireActivity(), observer);
        binding.rvEmployeesList.setAdapter(adapter);

        PieChart pie = binding.pcAdmin;
        ArrayList<PieEntry> pe = new ArrayList<>();

        LiveData<ArrayList<Ticket>> noOfOpenTickets = viewModel.noOfOpenTickets(this);
        final Observer<ArrayList<Ticket>> noOfOpenTicketsObserver = tickets -> {
            pe.add(new PieEntry(tickets.size(), "Opened"));
            showTicketsChart(pie, pe);
        };
        noOfOpenTickets.observe(requireActivity(), noOfOpenTicketsObserver);

        LiveData<ArrayList<Ticket>> noOfAssignedTickets = viewModel.noOfAssignedTickets(this);
        final Observer<ArrayList<Ticket>> noOfAssignedTicketsObserver = tickets -> {
            pe.add(new PieEntry(tickets.size(), "Assigned"));
            showTicketsChart(pie, pe);
        };
        noOfAssignedTickets.observe(requireActivity(), noOfAssignedTicketsObserver);

        LiveData<ArrayList<Ticket>> noOfClosedTickets = viewModel.noOfClosedTickets(this);
        final Observer<ArrayList<Ticket>> noOfClosedTicketsObserver = tickets -> {
            pe.add(new PieEntry(1, "Closed"));
            showTicketsChart(pie, pe);
        };
        noOfClosedTickets.observe(requireActivity(), noOfClosedTicketsObserver);

        return binding.getRoot();
    }

//    @Override
//    public void onAddEmployeeClick() {
//        Fragment addEmployeeForm = new HomeDashboardAddEmployeeFragment();
//        getFragmentManager().beginTransaction().replace(R.id.frame_holder, Objects.requireNonNull(addEmployeeForm)).commit();
//    }

    @Override
    public void onQueryReadError() {

    }

    @Override
    public void onQueryEmpty() {

    }

    @Override
    public void onQueryExistent() {

    }

    private void showTicketsChart(PieChart pie, ArrayList<PieEntry> pe) {
        pie.getDescription().setEnabled(false);
        pie.setRotationAngle(0);

        PieDataSet pieDataSet = new PieDataSet(pe, "");
        int[] colors = new int[]{Color.LTGRAY, Color.RED, Color.GREEN};
        pieDataSet.setColors(colors);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueTextSize(10);

        PieData pieData = new PieData(pieDataSet);
        pie.setData(pieData);
        pie.invalidate();
    }
}
