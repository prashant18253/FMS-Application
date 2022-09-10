package com.example.fmsio.fragment.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fmsio.R;
import com.example.fmsio.activity.admin.AdminLoginActivity;
import com.example.fmsio.databinding.FragmentAdminProfileDashboardBinding;
import com.example.fmsio.firebaseLiveData.DocumentCallBack;
import com.example.fmsio.model.User;
import com.example.fmsio.viewModel.admin.AdminMainViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileDashboardFragment extends Fragment implements DocumentCallBack {
    private FragmentAdminProfileDashboardBinding binding;
    private AdminMainViewModel viewModel;
    private FirebaseFirestore db;
    private String uid;
    Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_admin_profile_dashboard, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AdminMainViewModel.class);
        db = FirebaseFirestore.getInstance();
        uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        LiveData<User> user = viewModel.getAdminProfile(this);
        user.observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                String s = user.getImageUrl();
                new GetImageFromUrl(binding.image).execute(s);
                binding.number.setText(user.getPhoneNo());
                binding.name.setText(user.getName());
                binding.email.setText(user.getEmail());
                binding.email.setEnabled(false);
                binding.name.setEnabled(false);
                binding.number.setEnabled(false);
            }
        });

        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.name.setEnabled(true);
                binding.number.setEnabled(true);
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString();
                String number = binding.number.getText().toString();
                // Update one field, creating the document if it does not already exist.
                Map<String, Object> data = new HashMap<>();
                data.put("name", name);
                data.put("phoneNo", number);

                db.collection("admins").document(uid).set(data, SetOptions.merge());
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent myIntent = new Intent(getActivity(), AdminLoginActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(myIntent);
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onReadSuccess() {

    }

    @Override
    public void onReadFailure() {

    }
    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public GetImageFromUrl(ImageView img){
            this.imageView = img;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
