/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.mycompany.smart.campus.api.dao.*;
import com.mycompany.smart.campus.api.models.SensorModel;
import com.mycompany.smart.campus.api.models.RoomModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author rsoha
 */
@Path("/sensors")
public class SensorsResource {

    private GenericDAO<SensorModel> sensorDAO = new GenericDAO<>(MockDatabase.SENSORS);
    private GenericDAO<RoomModel> roomDAO = new GenericDAO<>(MockDatabase.ROOMS);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorModel> sensorList(@QueryParam("type") String type) {
        
        if (type == null) {
            return sensorDAO.getAll();
        }
        
        List<SensorModel> filteredSensorList = new ArrayList<>();
        
        for (SensorModel sensor : sensorDAO.getAll()) {
            if (type.equals(sensor.getType())) {
                filteredSensorList.add(sensor);
            }
        }
        
        return filteredSensorList;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensor(@Context UriInfo uriInfo,
            SensorModel sensor) {
        RoomModel room = roomDAO.getById(sensor.getRoom());

        if (room == null) {
            return Response.status(404).entity("Room id does not exist.").build();
        }

        sensorDAO.add(sensor);
        
        URI newSensorUri = uriInfo.getAbsolutePathBuilder()
                .path(sensor.getId())
                .build();


        return Response.created(newSensorUri).build();
    }
}
