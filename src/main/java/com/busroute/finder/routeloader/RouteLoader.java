package com.busroute.finder.routeloader;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by linaik on 2017/05/12.
 */
@Component
public class RouteLoader {

    private Map<Integer, Set<Integer>> routes = new HashMap<>();


    public void loadRouteDetails(String routeDataFile) throws IOException{

        File routeFile = new File(routeDataFile);
        if (!routeFile.exists()){
            throw new FileNotFoundException("File not found in the given location : " + routeDataFile);
        }

        loadRoutes(routeFile);

    }

    private void loadRoutes(File routeFile) throws IOException{
        Scanner input = new Scanner(routeFile);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] busLine = line.split(" ");
            Integer id = Integer.parseInt(busLine[0]);
            Set<Integer> lines = new HashSet<>();
            for(int i=1; i< busLine.length; i++){
                Integer stationId = Integer.parseInt(busLine[i]);
                lines.add(stationId);
            }
            routes.put(id,lines);
        }
        input.close();
    }

    public Map<Integer, Set<Integer>> getRoutes() {
        return routes;
    }
}
