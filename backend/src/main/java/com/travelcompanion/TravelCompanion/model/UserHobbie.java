package com.travelcompanion.TravelCompanion.model;

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
@Table(name = "user_hobbies")
public class UserHobbie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int firstUserId;
    private int secondUserId;
    private int total ;


}