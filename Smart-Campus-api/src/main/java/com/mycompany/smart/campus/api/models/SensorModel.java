/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.models;

/**
 *
 * @author rsoha
 */
public class SensorModel implements BaseModel {

    private String id;
    private String type;
    private String status;
    private double currentValue;
    private String roomId;

    // Constructor
    public SensorModel(String id, String type, String roomId) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
    }

    // Getters
    @Override
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public String getRoomId() {
        return roomId;
    }

    // Setters
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentValue(double value) {
        this.currentValue = value;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
