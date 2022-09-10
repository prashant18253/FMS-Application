package com.example.fmsio.fragment.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fmsio.R;
import com.example.fmsio.callBack.admin.fragment.AdminAddEmployeeFormCallBack;
import com.example.fmsio.databinding.FragmentAdminHomeDashboardAddEmployeesBinding;
import com.example.fmsio.databinding.FragmentAdminHomeDashboardBindingImpl;
import com.example.fmsio.repository.admin.AdminMainRepo;
import com.example.fmsio.viewModel.admin.AdminMainViewModel;

import java.util.Objects;

public class HomeDashboardAddEmployeeFragment extends Fragment implements AdminAddEmployeeFormCallBack, AdminMainRepo.AddEmployeeCallBack {
    private FragmentAdminHomeDashboardAddEmployeesBinding binding;
    private AdminMainViewModel viewModel;
    private ProgressDialog progressDialog;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_admin_home_dashboard_add_employees, container, false);
        binding.setContract(this);

        viewModel = new ViewModelProvider(requireActivity()).get(AdminMainViewModel.class);
        progressDialog = new ProgressDialog(requireActivity());

        return binding.getRoot();
    }

    @Override
    public void onAddEmployeeBtnClick() {
        viewModel.addEmployee(
                binding.etEmployeeName.getText().toString().trim(),
                binding.etEmployeeEmail.getText().toString().trim(),
                binding.etEmployeePhone.getText().toString().trim(),
                this);
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String msg) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
        Fragment adminHomeFragment = new HomeDashboardFragment();
        getFragmentManager().beginTransaction().replace(R.id.frame_holder, Objects.requireNonNull(adminHomeFragment)).commit();
    }
}
