package com.busroute.finder.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linaik on 2017/05/12.
 */

@AllArgsConstructor
@Getter
public class BusRouteResponse {

    @JsonProperty("dep_sid")
    private Integer departureStationId;

    @JsonProperty("arr_sid")
    private Integer arrivalStationId;

    @JsonProperty("direct_bus_route")
    private boolean directBusRoute;
}
