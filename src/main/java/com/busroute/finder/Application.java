package com.busroute.finder;

import com.busroute.finder.routeloader.RouteLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by linaik on 2017/05/12.
 */
@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private RouteLoader routeLoader;


    @Override
    public void run(String... strings) throws Exception {
        routeLoader.loadRouteDetails(strings[0]);
    }

    public static void main(String [] args) {
        if(args.length < 1){
            throw new IllegalArgumentException("Bus Route Data File is required...");
        }

        SpringApplication.run(Application.class, args);
    }
}
