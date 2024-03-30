// Declare package for this file
package com.energyxchange.EnergyXChange.services;

// Import necessary packages and classes

import com.energyxchange.EnergyXChange.model.Routes;
import com.energyxchange.EnergyXChange.payload.request.RegisterRequest;
import com.energyxchange.EnergyXChange.payload.request.RouteRequests;
import com.energyxchange.EnergyXChange.repository.RouteRepository;
import com.energyxchange.EnergyXChange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// Declare this class as a service and force the initialization of the final variables
@Service
@RequiredArgsConstructor
@CrossOrigin
public class RoutesService {
    private final RouteRepository routeRepository;

    public Routes createRoute(RouteRequests request,int userId,String userEmail) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        var route = Routes.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .userId(userId)
                .timestamp(formatter.format(date))
                .email(userEmail)
                .build();

        // Save the user in the repository
        return routeRepository.save(route);


    }

    public List<Routes> getAll(){
        List<Routes> routes = this.routeRepository.findAll();
        return routes;
    }


    public Routes getByUserIdAndCity(int userId,String city){
        Routes route = this.routeRepository.findRoutesByUserIdAndCity(userId,city);
        return route;
    }

}
