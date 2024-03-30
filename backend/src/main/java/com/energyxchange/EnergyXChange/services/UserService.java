// Declare package for this file
package com.energyxchange.EnergyXChange.services;

// Import necessary packages and classes

import com.energyxchange.EnergyXChange.config.JwtService;
import com.energyxchange.EnergyXChange.model.Role;
import com.energyxchange.EnergyXChange.model.User;
import com.energyxchange.EnergyXChange.payload.request.AuthenticationRequest;
import com.energyxchange.EnergyXChange.payload.request.RegisterRequest;
import com.energyxchange.EnergyXChange.payload.response.AuthenticationResponse;
import com.energyxchange.EnergyXChange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Declare this class as a service and force the initialization of the final variables
@Service
@RequiredArgsConstructor
@CrossOrigin
public class UserService {

    // Declare final variables for repositories, encoders, services and managers
    private final UserRepository userRepository;


    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        return user;
    }



    public int Calculate(User user1,User user2){
        List<String> user1List = new LinkedList<String>();
        if (user1.getHobbie1().length()>0) {
            user1List.add(user1.getHobbie1());
        }
        if (user1.getHobbie2().length()>0) {
            user1List.add(user1.getHobbie2());
        }
        if (user1.getHobbie3().length()>0) {
            user1List.add(user1.getHobbie3());
        }
        if (user1.getHobbie4().length()>0) {
            user1List.add(user1.getHobbie4());
        }


        List<String> user2List = new LinkedList<String>();

        if (user2.getHobbie1().length()>0) {
            user2List.add(user2.getHobbie1());
        }
        if (user2.getHobbie2().length()>0) {
            user2List.add(user2.getHobbie2());
        }
        if (user2.getHobbie3().length()>0) {
            user2List.add(user2.getHobbie3());
        }
        if (user2.getHobbie4().length()>0) {
            user2List.add(user2.getHobbie4());
        }

        int total = 0;

        for (String hobbie :user1List){
            if (user2List.contains(hobbie)){
                total += 1;
            }
        }


        return total;
    }

}
