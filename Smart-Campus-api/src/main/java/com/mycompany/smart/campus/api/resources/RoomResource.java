/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.mycompany.smart.campus.api.dao.MockDatabase;
import com.mycompany.smart.campus.api.dao.GenericDAO;
import com.mycompany.smart.campus.api.models.RoomModel;
import java.net.URI;
import javax.ws.rs.core.Response;

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

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoom(RoomModel room) {
        sensorDAO.add(room);

        URI newRoomUri = uriInfo.getAbsolutePathBuilder()
                .path(room.getId())
                .build();

        return Response.created(newRoomUri).build();
    }

    @DELETE
    @Path("/{roomId}")
    public String deleteRoom(@PathParam("roomId") String id) {
        sensorDAO.delete(id);

        return "Room with id = " + id + " has been deleted.";
    }
}
