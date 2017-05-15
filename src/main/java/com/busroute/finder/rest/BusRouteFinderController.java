package com.busroute.finder.rest;

import com.busroute.finder.service.BusRouteFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by linaik on 2017/05/12.
 */
@RestController
@RequestMapping("/api")
public class BusRouteFinderController {

    @Autowired
    BusRouteFinderService service;

    /**
     * Finds Direct Route between two stations.
     * @param departureStationId
     * @param arrivalStationId
     * @return
     */
    @RequestMapping(value = "/direct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody BusRouteResponse findDirectRoutes(@RequestParam("dep_sid") int departureStationId, @RequestParam("arr_sid") int arrivalStationId){

        boolean routeExists = service.isDirectRoute(departureStationId, arrivalStationId);
        return new BusRouteResponse(departureStationId, arrivalStationId, routeExists);
    }
}
