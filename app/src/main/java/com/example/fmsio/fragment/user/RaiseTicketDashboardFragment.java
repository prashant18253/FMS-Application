package com.example.fmsio.fragment.user;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fmsio.R;
import com.example.fmsio.callBack.user.fragment.UserRaiseTicketCallBack;
import com.example.fmsio.databinding.FragmentUserRaiseTicketDashboardBinding;
import com.example.fmsio.repository.user.UserMainRepo;
import com.example.fmsio.utils.KeyboardUtils;
import com.example.fmsio.viewModel.user.UserMainViewModel;

import java.util.Objects;


public class RaiseTicketDashboardFragment extends Fragment implements UserRaiseTicketCallBack, UserMainRepo.RaiseTicketCallBack{
    private FragmentUserRaiseTicketDashboardBinding binding;
    private UserMainViewModel viewModel;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_user_raise_ticket_dashboard, container, false);
        binding.setContract(this);

        viewModel = new ViewModelProvider(requireActivity()).get(UserMainViewModel.class);
        progressDialog = new ProgressDialog(requireActivity());
        setSpinners();
        return binding.getRoot();
    }

    @Override
    public void onRaiseTicketClick() {
        viewModel.raiseTicket(binding.spinServiceType.getSelectedItem().toString().trim(),
                binding.spinBuildingName.getSelectedItem().toString().trim(),
                binding.etFloorNo.getText().toString().trim(),binding.etRoomNo.getText().toString().trim(),
                binding.etDescription.getText().toString(), this);
    }

    @Override
    public void onValidate(int flag, String msg) {
        if(flag == 1){
            KeyboardUtils.hideKeyboard(requireActivity());
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_layout);
            progressDialog.setCancelable(false);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        }else{
            Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(String msg) {
        progressDialog.dismiss();
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
        setDefault();
    }

    @Override
    public void onSuccess(String msg) {
        progressDialog.dismiss();
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
        setDefault();
    }

    public void setSpinners(){
        ArrayAdapter<CharSequence> buildingNameAdapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.building_names, android.R.layout.simple_spinner_item);
        buildingNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinBuildingName.setAdapter(buildingNameAdapter);

        ArrayAdapter<CharSequence> serviceTypeAdapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.service_types, android.R.layout.simple_spinner_item);
        serviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinServiceType.setAdapter(serviceTypeAdapter);
    }

    private void setDefault(){
        binding.spinServiceType.setSelection(0);
        binding.spinBuildingName.setSelection(0);
        binding.etFloorNo.setText("");
        binding.etRoomNo.setText("");
        binding.etDescription.setText("");
    }
}