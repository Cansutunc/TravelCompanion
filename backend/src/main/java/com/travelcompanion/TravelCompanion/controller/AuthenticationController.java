// Import required classes and libraries
package com.travelcompanion.TravelCompanion.controller;
import com.travelcompanion.TravelCompanion.payload.response.AuthenticationResponse;
import com.travelcompanion.TravelCompanion.services.AuthenticationService;
import com.travelcompanion.TravelCompanion.payload.request.AuthenticationRequest;
import com.travelcompanion.TravelCompanion.payload.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Mark class as REST controller with base URL
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    //register
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request){
        // Return HTTP response with registered user's authentication details
        System.out.printf(request.getEmail(),request.getName(),request.getPassword());
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

    // Handle POST requests to authenticate a user
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        // Return HTTP response with authenticated user's details
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
