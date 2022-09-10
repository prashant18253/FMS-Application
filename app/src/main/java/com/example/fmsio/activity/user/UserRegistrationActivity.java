package com.example.fmsio.activity.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.fmsio.R;
import com.example.fmsio.callBack.user.activity.UserRegisterCallBack;
import com.example.fmsio.databinding.ActivityUserRegistrationBinding;
import com.example.fmsio.repository.user.UserRegisterRepo;
import com.example.fmsio.viewModel.user.UserRegisterViewModel;

import java.util.Objects;

public class UserRegistrationActivity extends AppCompatActivity implements UserRegisterCallBack, UserRegisterRepo.RegistrationCallBack {
    private ActivityUserRegistrationBinding binding;
    private UserRegisterViewModel viewModel;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_registration);
        binding.setContract(this);

        viewModel = new ViewModelProvider(this).get(UserRegisterViewModel.class);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onRegisterClick() {
        viewModel.register(binding.etUserName.getText().toString().trim(), binding.etUserEmail.getText().toString().trim(), binding.etUserPhone.getText().toString().trim(), binding.etUserPassword.getText().toString().trim(), binding.etUserConfPassword.getText().toString().trim(), this);
    }


    @Override
    public void validate(int flag, String text) {
        if(flag == 0){
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }else if(flag == 1){
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.setContentView(R.layout.progress_layout);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        }else if(flag == 2){
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            binding.etUserConfPassword.setText("");
            binding.etUserPassword.setText("");
        }
    }

    @Override
    public void onSuccess(int flag, String text) {
        progressDialog.dismiss();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserRegistrationActivity.this,UserLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(String text) {
        progressDialog.dismiss();
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
        binding.etUserEmail.setText("");
        binding.etUserPhone.setText("");
        binding.etUserName.setText("");
        binding.etUserConfPassword.setText("");
        binding.etUserPassword.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}