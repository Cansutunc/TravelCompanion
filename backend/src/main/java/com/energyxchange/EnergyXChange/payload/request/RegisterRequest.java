// Package declaration for the RegisterRequest class
package com.energyxchange.EnergyXChange.payload.request;

// Lombok annotations to generate boilerplate code for getters, setters, constructors, and builder methods
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data object representing a registration request
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    // Fields representing the data to be included in the registration request
    private String email;
    private String name;
    private String hobbie1;
    private String hobbie2;
    private String hobbie3;
    private String hobbie4;

    private String password;
}