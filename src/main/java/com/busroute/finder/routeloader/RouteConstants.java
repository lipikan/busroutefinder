package com.busroute.finder.routeloader;

import java.util.regex.Pattern;

/**
 * Created by linaik on 2017/05/14.
 */
public class RouteConstants {

    public static final long MAX_ROUTES_ALLOWED = 100000;

    public static final long MAX_STATIONS_PER_ROUTE = 1000;

    public static final long MAX_STATION_ALLOWED = 1000000;

    public static final Pattern WHITE_SPACE_PATTERN = Pattern.compile("\\s+");
}
