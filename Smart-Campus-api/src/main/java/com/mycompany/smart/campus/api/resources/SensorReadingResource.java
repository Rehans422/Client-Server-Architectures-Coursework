/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.mycompany.smart.campus.api.dao.*;
import com.mycompany.smart.campus.api.models.SensorReadingModel;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

/**
 *
 * @author rsoha
 */
public class SensorReadingResource {

    private GenericDAO<SensorReadingModel> readingDAO = new GenericDAO<>(MockDatabase.READINGS);
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReadingModel> getAllReadings() {
        List<SensorReadingModel> response = new ArrayList<>();

        for (SensorReadingModel reading : readingDAO.getAll()) {
            System.out.println(reading);
            if (sensorId.equals(reading.getSensorId())) {
                response.add(reading);
            }
        }

        return response;
    }

    @GET
    @Path("{rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReadingModel getReading(@PathParam("rid") String rid) {
        SensorReadingModel reading = readingDAO.getById(rid);

        if (reading == null || !sensorId.equals(reading.getSensorId())) {
            throw new NotFoundException("Reading not found for this sensor");
        }

        return reading;
    }
}
