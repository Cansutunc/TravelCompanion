// Import necessary classes
package com.travelcompanion.TravelCompanion.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.model.UserHobbie;
import com.travelcompanion.TravelCompanion.repository.UserHobbieRepository;
import org.springframework.http.HttpStatus;
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
    public UserController(UserRepository userRepository, UserHobbieRepository userHobbieRepository) {
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

    @GetMapping("/getHobbies/{email}") // Maps this method to GET /api/v1/user/getHobbies/{email}
    public ResponseEntity<?> getHobbies(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Collect hobbies into a list
        List<String> userHobbies = new ArrayList<>();
        if (user.getHobbie1() != null) userHobbies.add(user.getHobbie1());
        if (user.getHobbie2() != null) userHobbies.add(user.getHobbie2());
        if (user.getHobbie3() != null) userHobbies.add(user.getHobbie3());
        if (user.getHobbie4() != null) userHobbies.add(user.getHobbie4());


        return ResponseEntity.ok(userHobbies);
    }

    @PutMapping("/updateHobbies/{email}")
    public ResponseEntity<?> updateUserHobbies(
            @PathVariable String email,
            @RequestBody Map<String, String> hobbies) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (hobbies.containsKey("hobbie1")) user.setHobbie1(hobbies.get("hobbie1"));
            if (hobbies.containsKey("hobbie2")) user.setHobbie2(hobbies.get("hobbie2"));
            if (hobbies.containsKey("hobbie3")) user.setHobbie3(hobbies.get("hobbie3"));
            if (hobbies.containsKey("hobbie4")) user.setHobbie4(hobbies.get("hobbie4"));
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
