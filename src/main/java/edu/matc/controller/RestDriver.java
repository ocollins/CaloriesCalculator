package edu.matc.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by student on 3/31/17.
 */
@ApplicationPath("/")
public class RestDriver extends Application {

    /**
     * Created by student on 2/26/17.
     */
//Defines the base URI for all resource URIs.


    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(ActivitiesRest.class);
        h.add(DurationRest.class);
        return h;
    }
}