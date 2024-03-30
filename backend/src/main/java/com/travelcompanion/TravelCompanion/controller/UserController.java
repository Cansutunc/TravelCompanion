// Import necessary classes
package com.travelcompanion.TravelCompanion.controller;
import java.util.List;

import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.model.UserHobbie;
import com.travelcompanion.TravelCompanion.repository.UserHobbieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travelcompanion.TravelCompanion.repository.UserRepository;

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
