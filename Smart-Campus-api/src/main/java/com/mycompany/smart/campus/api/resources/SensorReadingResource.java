/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import javax.ws.rs.*;
import com.mycompany.smart.campus.api.dao.*;
import com.mycompany.smart.campus.api.models.SensorReadingModel;
import com.mycompany.smart.campus.api.models.SensorModel;
import com.mycompany.smart.campus.api.exceptions.SensorUnavailableException;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 *
 * @author rsoha
 */
public class SensorReadingResource {

    private GenericDAO<SensorReadingModel> readingDAO = new GenericDAO<>(MockDatabase.READINGS);
    private GenericDAO<SensorModel> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReadingModel> getAllReadings() {
        List<SensorReadingModel> response = new ArrayList<>();

        for (SensorReadingModel reading : readingDAO.getAll()) {
            if (sensorId.equals(reading.getSensorId())) {
                response.add(reading);
            }
        }

        return response;
    }

    @GET
    @Path("/{rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public SensorReadingModel getReading(@PathParam("rid") String rid) {
        SensorReadingModel reading = readingDAO.getById(rid);

        if (reading == null || !sensorId.equals(reading.getSensorId())) {
            throw new NotFoundException("Reading not found for this sensor");
        }

        return reading;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(@Context UriInfo uriInfo,
            SensorReadingModel reading) {
        SensorModel sensor = sensorDAO.getById(sensorId);

        if (sensor == null) {
            return Response.status(404).entity("Sensor with id " + sensorId + " not found.").build();
        } else if (sensor.getStatus().equals("MAINTENANCE")) {
            throw new SensorUnavailableException("Sensor with id " + sensorId + " is currently undergoing maintenance.");
        }

        reading.setSensorId(sensorId);

        readingDAO.add(reading);

        sensor.setCurrentValue(reading.getValue());
        sensorDAO.update(sensor);

        URI newSensorReadingUri = uriInfo.getAbsolutePathBuilder()
                .path(reading.getId())
                .build();

        return Response.created(newSensorReadingUri).entity(reading).build();
    }
}
