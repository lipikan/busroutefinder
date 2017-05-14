package com.busroute.finder.service;

import com.busroute.finder.routeloader.RouteLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by linaik on 2017/05/12.
 */
@Component
public class BusRouteFinderService {

    @Autowired
    private RouteLoader routeLoader;

    public boolean isDirectRoute(int departureStationId, int arrivalStationId) {

        Map<Integer, Set<Integer>> routes = routeLoader.getRoutes();
        Set<Integer> departureRoutes = new HashSet<>();
        Set<Integer> arrivalRoutes = new HashSet<>();

        for(Integer routeId: routes.keySet()) {
            if (routes.get(routeId).contains(departureStationId)) {
                departureRoutes.add(routeId);
            }

            if(routes.get(routeId).contains(arrivalStationId)){
                arrivalRoutes.add(routeId);
            }
        }

        for(Integer depRouteId :departureRoutes){
            if(arrivalRoutes.contains(depRouteId)){
               if(routes.get(depRouteId).contains(arrivalStationId) && routes.get(depRouteId).contains(departureStationId)){
                   return true;
               }
            }
        }
        return false;
    }

}
