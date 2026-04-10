/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import com.mycompany.smart.campus.api.dao.*;
import com.mycompany.smart.campus.api.models.SensorModel;
import com.mycompany.smart.campus.api.models.RoomModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import javax.ws.rs.core.Context;

/**
 *
 * @author rsoha
 */
public class SensorsResource {

    private GenericDAO<SensorModel> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);
    private GenericDAO<RoomModel> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(SensorModel sensor) {
        RoomModel room = roomDAO.getById(Integer.parseInt(sensor.getRoom()));

        if (room == null) {
            return Response.status(404).entity("Room id does not exist.").build();
        }

        URI newSensorUri = uriInfo.getAbsolutePathBuilder()
                .path(sensor.getId())
                .build();

        sensorDAO.add(sensor);
        return Response.created(newSensorUri).build();
    }
}
