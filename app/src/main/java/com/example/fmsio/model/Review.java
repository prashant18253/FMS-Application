package com.example.fmsio.model;

public class Review {
    private String id;
    private String title;
    private float rating;
    private String content;
    private String ticketId;
    private String creatorId;
    private String assignedId;
    private String datetime;

    public Review(){}

    public Review(String id, String title, float rating, String content, String ticketId, String creatorId, String assignedId, String datetime) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.ticketId = ticketId;
        this.creatorId = creatorId;
        this.assignedId = assignedId;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(String assignedId) {
        this.assignedId = assignedId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
