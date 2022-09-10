package com.example.fmsio.viewModel.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fmsio.firebaseLiveData.DocumentCallBack;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.model.User;
import com.example.fmsio.repository.user.UserMainRepo;

import java.util.ArrayList;

public class UserMainViewModel extends ViewModel {
    public void raiseTicket(String serviceType, String buildingName, String floorNo, String roomNo, String description, UserMainRepo.RaiseTicketCallBack callBack){
        UserMainRepo.raiseTicket(serviceType, buildingName, floorNo, roomNo, description, callBack);
    }

    public LiveData<ArrayList<User>> getAllEmployees(QueryCallBack callBack){
        return UserMainRepo.getAllEmployess(callBack);
    }
    public LiveData<User> getUserProfile(DocumentCallBack callBack){
        return UserMainRepo.getUserProfile(callBack);
    }
    public LiveData<ArrayList<Ticket>> getNonClosedTickets(QueryCallBack callBack) {
        return UserMainRepo.getNonCloseTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getClosedTickets(QueryCallBack callBack) {
        return UserMainRepo.getClosedTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getAllTickets(QueryCallBack callBack){
        return UserMainRepo.getAllTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getOpenedTickets(QueryCallBack callBack){
        return UserMainRepo.getOpenedTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getAssignedTickets(QueryCallBack callBack){
        return UserMainRepo.getAssignedTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getLowTickets(QueryCallBack callBack){
        return UserMainRepo.getLowTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getMediumTickets(QueryCallBack callBack){
        return UserMainRepo.getMediumTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getHighTickets(QueryCallBack callBack){
        return UserMainRepo.getHighTickets(callBack);
    }

    public LiveData<ArrayList<User>> getAllEmployess(QueryCallBack callBack) {
        return UserMainRepo.getAllEmployess(callBack);
    }

    public void addEmployee(String employeeName, String email, String phone, UserMainRepo.AddEmployeeCallBack callBack) {
        UserMainRepo.addEmployee(employeeName, email, phone, callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfOpenTickets(QueryCallBack callBack) {
        return UserMainRepo.noOfOpenTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfAssignedTickets(QueryCallBack callBack) {
        return UserMainRepo.noOfAssignedTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfClosedTickets(QueryCallBack callBack) {
        return UserMainRepo.noOfClosedTickets(callBack);
    }
}
