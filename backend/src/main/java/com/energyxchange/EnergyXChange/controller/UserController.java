// Import necessary classes
package com.energyxchange.EnergyXChange.controller;
import java.util.List;
import java.util.Optional;

import com.energyxchange.EnergyXChange.model.User;
import com.energyxchange.EnergyXChange.model.UserHobbie;
import com.energyxchange.EnergyXChange.repository.UserHobbieRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.energyxchange.EnergyXChange.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/user") // Map requests to /api/v1/buyer to this controller
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final UserHobbieRepository userHobbieRepository;

    //constructor
     public UserController(UserRepository userRepository,UserHobbieRepository userHobbieRepository) {
         this.userHobbieRepository = userHobbieRepository;
        this.userRepository = userRepository;

    }

    @GetMapping("/byEmail/{email}")// Maps this method to GET /api/v1/user/{email}
    public ResponseEntity<?> getUser(@PathVariable("email") String email) {

        User user = userRepository.findByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.ok("");
    }

    @GetMapping("/getHobbies/{email}")// Maps this method to GET /api/v1/user/{email}
    public ResponseEntity<?> getHobbies(@PathVariable("email") String email) {

        User user = userRepository.findByEmail(email);

        List<UserHobbie>  userHobbies = userHobbieRepository.findByFirstUserIdOrderByTotalDesc(user.getId());

        return ResponseEntity.ok(userHobbies);
    }
    
}
