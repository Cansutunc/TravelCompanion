// Declare package for this file
package com.travelcompanion.TravelCompanion.services;

// Import necessary packages and classes
import com.travelcompanion.TravelCompanion.config.JwtService;
import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.model.Role;
import com.travelcompanion.TravelCompanion.payload.request.AuthenticationRequest;
import com.travelcompanion.TravelCompanion.payload.request.RegisterRequest;
import com.travelcompanion.TravelCompanion.payload.response.AuthenticationResponse;
import com.travelcompanion.TravelCompanion.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

// Declare this class as a service and force the initialization of the final variables
@Service
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationService {

    // Declare final variables for repositories, encoders, services and managers
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(RegisterRequest request) {

        // Create a new user object using the input request
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .hobbie1(request.getHobbie1())
                .hobbie2(request.getHobbie2())
                .hobbie3(request.getHobbie3())
                .hobbie4(request.getHobbie4())
                .build();

        // Save the user in the repository
        userRepository.save(user);

        // Generate a JWT token for the user
        var jwtToken = jwtService.generateToken(user);

        // Return an authentication response containing the JWT token
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    // Method to authenticate an existing user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // Authenticate the user using the authentication manager and their email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail());

        if (user != null){
            // Generate a JWT token for the user
            var jwtToken = jwtService.generateToken((UserDetails) user);

            // Return an authentication response containing the JWT token
            return AuthenticationResponse.builder().token(jwtToken).build();
        }

        // Return an authentication response containing the JWT token
         return AuthenticationResponse.builder().token("null").build();
    
    }

}
