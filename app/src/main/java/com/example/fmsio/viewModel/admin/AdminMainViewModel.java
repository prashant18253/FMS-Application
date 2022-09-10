package com.example.fmsio.viewModel.admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fmsio.firebaseLiveData.DocumentCallBack;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.fragment.admin.AllTicketsDashboardFragment;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.model.User;
import com.example.fmsio.repository.admin.AdminMainRepo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminMainViewModel extends ViewModel {
    public void raiseTicket(String serviceType, String buildingName, String floorNo, String roomNo, String description, AdminMainRepo.RaiseTicketCallBack callBack){
        AdminMainRepo.raiseTicket(serviceType, buildingName, floorNo, roomNo, description, callBack);
    }

    public LiveData<ArrayList<User>> getAllEmployees(QueryCallBack callBack){
        return AdminMainRepo.getAllEmployess(callBack);
    }
    public LiveData<User> getAdminProfile(DocumentCallBack callBack){
        return AdminMainRepo.getAdminProfile(callBack);
    }
    public LiveData<ArrayList<Ticket>> getNonClosedTickets(QueryCallBack callBack) {
        return AdminMainRepo.getNonCloseTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getClosedTickets(QueryCallBack callBack) {
        return AdminMainRepo.getClosedTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getAllTickets(QueryCallBack callBack){
        return AdminMainRepo.getAllTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getOpenedTickets(QueryCallBack callBack){
        return AdminMainRepo.getOpenedTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getAssignedTickets(QueryCallBack callBack){
        return AdminMainRepo.getAssignedTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getLowTickets(QueryCallBack callBack){
        return AdminMainRepo.getLowTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getMediumTickets(QueryCallBack callBack){
        return AdminMainRepo.getMediumTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getHighTickets(QueryCallBack callBack){
        return AdminMainRepo.getHighTickets(callBack);
    }

    public LiveData<ArrayList<User>> getAllEmployess(QueryCallBack callBack) {
        return AdminMainRepo.getAllEmployess(callBack);
    }

    public void addEmployee(String employeeName, String email, String phone, AdminMainRepo.AddEmployeeCallBack callBack) {
        AdminMainRepo.addEmployee(employeeName, email, phone, callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfOpenTickets(QueryCallBack callBack) {
        return AdminMainRepo.noOfOpenTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfAssignedTickets(QueryCallBack callBack) {
        return AdminMainRepo.noOfAssignedTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfClosedTickets(QueryCallBack callBack) {
        return AdminMainRepo.noOfClosedTickets(callBack);
    }
}
