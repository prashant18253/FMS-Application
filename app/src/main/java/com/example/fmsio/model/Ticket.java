package com.example.fmsio.model;

public class Ticket {
    private String id;
    private String type;
    private String adminId;
    private String creatorId;
    private String assignedTo;
    private String name;
    private String datetime;
    private String status;
    private int roomNo;
    private String hostelName;
    private int floorNo;
    private String description;
    private String ETA;
    private String priority;
    private String otpSignature;

    public Ticket(){}

    public Ticket(String id, String type, String adminId, String creatorId, String name, String datetime, int roomNo, String hostelName, int floorNo, String  description){
        this.id = id;
        this.type = type;
        this.adminId = adminId;
        this.creatorId = creatorId;
        this.assignedTo = "";
        this.name = name;
        this.datetime = datetime;
        this.status = "opened";
        this.roomNo = roomNo;
        this.hostelName = hostelName;
        this.floorNo = floorNo;
        this.description = description;
        this.ETA = "";
        this.priority = "medium";
        this.otpSignature = generateOTP();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getETA() {
        return ETA;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getOtpSignature() {
        return otpSignature;
    }

    public void setOtpSignature(String otpSignature) {
        this.otpSignature = otpSignature;
    }

    private String generateOTP(){
        return "demo";
    }

}
