// Specifies the package where the class is located
package com.travelcompanion.TravelCompanion.payload.request;

// Import Lombok annotations
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Add Lombok annotations to the class
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// Define the class AuthenticationResponse
public class RouteRequests {
    private String city;
    private String country;
    private String email;
}