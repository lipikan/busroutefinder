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

    /**
     * This method verifies whether there is a direct route between two station.
     * @param departureStationId
     * @param arrivalStationId
     * @return
     */
    public boolean isDirectRoute(int departureStationId, int arrivalStationId) {

        Map<Integer, Set<Integer>> routes = routeLoader.getRoutes();
        Set<Integer> departureRoutes = new HashSet<>();
        Set<Integer> arrivalRoutes = new HashSet<>();

        if(!checkStationExists(departureStationId,routes) || !checkStationExists(arrivalStationId, routes)){
            return false;
        }

        for(Map.Entry<Integer, Set<Integer>> entry: routes.entrySet()) {
            if (entry.getValue().contains(departureStationId)) {
                departureRoutes.add(entry.getKey());
            }

            if(entry.getValue().contains(arrivalStationId)){
                arrivalRoutes.add(entry.getKey());
            }
        }

        for(Integer depRouteId :departureRoutes){
            if(arrivalRoutes.contains(depRouteId) && routes.get(depRouteId).contains(arrivalStationId)){
                return true;
            }
        }
        return false;
    }


    private boolean checkStationExists(int stationId, Map<Integer, Set<Integer>> routes) {
        for (Map.Entry<Integer, Set<Integer>> entry: routes.entrySet()){
            if (entry.getValue().contains(stationId)) {
                return true;
            }
        }
        return false;
    }
}
