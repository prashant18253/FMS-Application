package com.example.fmsio.adapter.employee.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fmsio.R;
import com.example.fmsio.databinding.CardAdminEmployeesListItemBinding;
import com.example.fmsio.model.User;

import java.util.ArrayList;

public class HomeDashboardFragmentAdapter extends RecyclerView.Adapter<HomeDashboardFragmentAdapter.Holder> {
    private ArrayList<User> users;
    private Context context;

    public HomeDashboardFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeDashboardFragmentAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardAdminEmployeesListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.card_admin_employees_list_item, parent,false);
        return new HomeDashboardFragmentAdapter.Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeDashboardFragmentAdapter.Holder holder, int position) {
        User user = users.get(position);
        Glide.with(context).load(user.getImageUrl()).into(holder.binding.ivEmployeeImage);
        holder.binding.tvEmployeeName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if (users != null) return users.size();
        return 0;
    }

    static class Holder extends RecyclerView.ViewHolder {
        final private CardAdminEmployeesListItemBinding binding;

        Holder(@NonNull CardAdminEmployeesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
