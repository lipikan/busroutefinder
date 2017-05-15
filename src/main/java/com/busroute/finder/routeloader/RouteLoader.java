package com.busroute.finder.routeloader;

import com.busroute.finder.exception.InvalidDataFileException;
import static com.busroute.finder.routeloader.RouteConstants.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by linaik on 2017/05/12.
 */
@Component
public class RouteLoader {

    private Map<Integer, Set<Integer>> routes = new HashMap<>();


    /**
     * Loads the routes from a given Data File.
     * @param routeDataFile
     * @throws IOException
     */
    public void loadRouteDetails(String routeDataFile) throws IOException, InvalidDataFileException{

        File routeFile = new File(routeDataFile);
        if (!routeFile.exists()){
            throw new FileNotFoundException("File not found in the given location : " + routeDataFile);
        }
        verifyDataFile(routeDataFile);
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

    private void verifyDataFile(String routeDataFile) throws InvalidDataFileException, IOException
    {
        List<String> lines = Files.lines(Paths.get(routeDataFile)).map(line -> line.trim()).collect(Collectors.toList());

        long numberOfRoutes = lines.stream().findFirst().map(Long::valueOf).get();

        if(numberOfRoutes > MAX_ROUTES_ALLOWED) {
            throw new InvalidDataFileException("Total Routes Exceeded the Maximum limit");
        }

        long actualNumberOfRoutes = lines.stream().filter(line -> !StringUtils.isEmpty(line)).skip(1).count();

        if(numberOfRoutes != actualNumberOfRoutes){
            throw new InvalidDataFileException("Route Count Mismatch: Total Routes: " + numberOfRoutes + " Actual Routes Found: " + actualNumberOfRoutes);
        }

        Set<Integer> distinctStationIds = lines.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> WHITE_SPACE_PATTERN.splitAsStream(line).skip(1).map(Integer::valueOf).collect(Collectors.toList()))
                .flatMap(x -> x.stream())
                .collect(Collectors.toSet());

        if (distinctStationIds.size() > MAX_STATION_ALLOWED) {
            throw new InvalidDataFileException("Max Station Allowed exceeded its limit.");
        }

        boolean maxStationsPerRouteExceeds = lines.stream()
                .filter(line -> !StringUtils.isEmpty(line))
                .skip(1)
                .map(line -> WHITE_SPACE_PATTERN.splitAsStream(line).skip(1).map(Integer::valueOf).collect(Collectors.toList()))
                .anyMatch(routeInfo -> routeInfo.size() > MAX_STATIONS_PER_ROUTE);

        if (maxStationsPerRouteExceeds) {
            throw new InvalidDataFileException("Max station allowed per route exceeded.");
        }
    }

    public Map<Integer, Set<Integer>> getRoutes() {
        return routes;
    }
}
