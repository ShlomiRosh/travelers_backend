package com.travelers.travelers_backend.model;


public class Traveler {

    private String id;

    private boolean isGreen;

    public Traveler(String id, boolean green) {
        this.id = id;
        this.isGreen = green;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public void setGreen(boolean green) {
        this.isGreen = green;
    }
}
