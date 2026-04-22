/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.dao;

import com.mycompany.smart.campus.api.models.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rsoha
 */
public class MockDatabase {

    public static final List<RoomModel> ROOMS = new ArrayList<>();
    public static final List<SensorModel> SENSORS = new ArrayList<>();
    public static final List<SensorReadingModel> READINGS = new ArrayList<>();

    static {
        // Initialising Rooms
        ROOMS.add(new RoomModel("1", "Lab A", 30));
        ROOMS.add(new RoomModel("2", "Lecture Hall", 100));

        // Initialising Sensors
        SENSORS.add(new SensorModel("1", "Temperature", "1"));
        SENSORS.add(new SensorModel("2", "CO2", "2"));
        
        for (SensorModel SENSOR : SENSORS) {
            String roomId = SENSOR.getRoomId();
            
            for (RoomModel ROOM : ROOMS) {
                if (ROOM.getId().equals(roomId)) {
                    List<String> roomSensors = ROOM.getSensorIds();
                    roomSensors.add(SENSOR.getId());
                    ROOM.setSensorIds(roomSensors);
                }
            }
        }

        // Initialising Sensor Readings
        READINGS.add(new SensorReadingModel("1", "1", 3000, 8));
        READINGS.add(new SensorReadingModel("2", "2" , 54000, 22));
    }
}
