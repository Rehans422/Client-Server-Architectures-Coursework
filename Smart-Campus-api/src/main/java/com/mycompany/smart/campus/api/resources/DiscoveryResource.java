/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.resources;

import java.util.Map;
import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rsoha
 */
@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> apiInfo() {
        Map<String, Object> response = new HashMap<>();

        response.put("version", "1.0");
        response.put("contact", "admin@smartcampus.com");

        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "http://localhost:8080/Smart-Campus-api/api/v1/rooms");
        resources.put("sensors", "http://localhost:800/Smart-Campus-api/api/v1/sensors");

        response.put("resources", resources);

        return response;
    }
}
