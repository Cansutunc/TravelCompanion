// Import necessary classes
package com.travelcompanion.TravelCompanion.controller;

import java.util.List;

import com.travelcompanion.TravelCompanion.model.Routes;
import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.payload.request.RouteRequests;
import com.travelcompanion.TravelCompanion.services.RoutesService;
import com.travelcompanion.TravelCompanion.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;


// Mark class as REST controller with base URL
@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
@CrossOrigin
public class RoutesController {

    private final UserService userService;
    private final RoutesService routesService;

    // Handle POST requests to register a buyer


    @GetMapping("/getAll")
    @Transactional
    public ResponseEntity<?> viewTransactions() {
        List<Routes> routes = routesService.getAll();
        return  ResponseEntity.ok("routes");
    }

    @PostMapping("/update")
    public ResponseEntity registerUser(@RequestBody RouteRequests request){
        if (request.getCity() == null || request.getCountry()== null) {
            return ResponseEntity.ok("");
        }

        User user = userService.getUserByEmail(request.getEmail());
        Routes existRoute = routesService.getByUserIdAndCity(user.getId(),request.getCity());
        if (existRoute == null){
            Routes route = routesService.createRoute(request,user.getId(),user.getEmail());
            return ResponseEntity.ok(route.getId());
        }
        return ResponseEntity.ok(existRoute.getId());

    }



}