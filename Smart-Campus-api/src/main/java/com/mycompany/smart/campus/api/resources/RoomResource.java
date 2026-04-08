/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.mycompany.smart.campus.api.dao.MockDatabase;
import com.mycompany.smart.campus.api.dao.GenericDAO;
import com.mycompany.smart.campus.api.models.RoomModel;

/**
 *
 * @author rsoha
 */
@Path("/rooms")
public class RoomResource {

    private GenericDAO<RoomModel> sensorDAO = new GenericDAO<>(MockDatabase.ROOMS);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoomModel> roomList() {
        return sensorDAO.getAll();
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RoomModel getRoomById(@PathParam("roomId") int roomId) {
        return sensorDAO.getById(roomId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addRoom(RoomModel room) {
        sensorDAO.add(room);
    }
}
