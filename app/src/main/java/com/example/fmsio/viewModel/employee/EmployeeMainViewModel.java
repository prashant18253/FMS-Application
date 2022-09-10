package com.example.fmsio.viewModel.employee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fmsio.firebaseLiveData.DocumentCallBack;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.model.User;
import com.example.fmsio.repository.employee.EmployeeMainRepo;

import java.util.ArrayList;

public class EmployeeMainViewModel extends ViewModel {
    public void raiseTicket(String serviceType, String buildingName, String floorNo, String roomNo, String description, EmployeeMainRepo.RaiseTicketCallBack callBack){
        EmployeeMainRepo.raiseTicket(serviceType, buildingName, floorNo, roomNo, description, callBack);
    }

    public LiveData<ArrayList<User>> getAllEmployees(QueryCallBack callBack){
        return EmployeeMainRepo.getAllEmployess(callBack);
    }
    public LiveData<User> getAdminProfile(DocumentCallBack callBack){
        return EmployeeMainRepo.getAdminProfile(callBack);
    }
    public LiveData<ArrayList<Ticket>> getNonClosedTickets(QueryCallBack callBack) {
        return EmployeeMainRepo.getNonCloseTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getClosedTickets(QueryCallBack callBack) {
        return EmployeeMainRepo.getClosedTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getAllTickets(QueryCallBack callBack){
        return EmployeeMainRepo.getAllTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getOpenedTickets(QueryCallBack callBack){
        return EmployeeMainRepo.getOpenedTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getAssignedTickets(QueryCallBack callBack){
        return EmployeeMainRepo.getAssignedTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> getLowTickets(QueryCallBack callBack){
        return EmployeeMainRepo.getLowTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getMediumTickets(QueryCallBack callBack){
        return EmployeeMainRepo.getMediumTickets(callBack);
    }
    public LiveData<ArrayList<Ticket>> getHighTickets(QueryCallBack callBack){
        return EmployeeMainRepo.getHighTickets(callBack);
    }

    public LiveData<ArrayList<User>> getAllEmployess(QueryCallBack callBack) {
        return EmployeeMainRepo.getAllEmployess(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfOpenTickets(QueryCallBack callBack) {
        return EmployeeMainRepo.noOfOpenTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfAssignedTickets(QueryCallBack callBack) {
        return EmployeeMainRepo.noOfAssignedTickets(callBack);
    }

    public LiveData<ArrayList<Ticket>> noOfClosedTickets(QueryCallBack callBack) {
        return EmployeeMainRepo.noOfClosedTickets(callBack);
    }
}
