/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.models;

/**
 *
 * @author rsoha
 */
public class SensorReadingModel implements BaseModel {

    private String id;
    private String sensorId;
    private long timestamp;
    private double value;

    // Contructor
    public SensorReadingModel(String id, String sensorId, long timestamp, double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.value = value;
    }

    // Getters
    @Override
    public String getId() {
        return id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    // Setters
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
