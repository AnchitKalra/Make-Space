package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private int capacity;
    private String name;

    private boolean isBooked = false;

    private List<String> bookedList;

    private Map<String, String> bookedMap;

    public Room() {
        bookedList = new ArrayList<>();
        bookedMap = new HashMap<>();
    }

    public List<String> getBookedList() {
        return bookedList;
    }

    public void setBookedList(List<String> bookedList) {
        this.bookedList = bookedList;
    }

    public Map<String, String> getBookedMap() {
        return bookedMap;
    }

    public void setBookedMap(Map<String, String> bookedMap) {
        this.bookedMap = bookedMap;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
