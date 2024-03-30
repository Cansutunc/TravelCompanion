package com.energyxchange.EnergyXChange.model;

import jakarta.persistence.*;
import lombok.*;

// Define the Seller entity
@Entity

@Getter @Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

// Map the entity to a table named "buyers"
@Table(name = "routes")
public class Routes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;
    private String country;
    private int userId;
    private String timestamp;
    private String email;



}