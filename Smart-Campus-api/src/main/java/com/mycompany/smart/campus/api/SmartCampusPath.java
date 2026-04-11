/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api;

import com.mycompany.smart.campus.api.resources.DiscoveryResource;
import com.mycompany.smart.campus.api.resources.RoomResource;
import com.mycompany.smart.campus.api.resources.SensorsResource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rsoha
 */
@ApplicationPath("/api/v1")
public class SmartCampusPath extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(DiscoveryResource.class);
        classes.add(RoomResource.class);
        classes.add(SensorsResource.class);
        return classes;
    }
}
