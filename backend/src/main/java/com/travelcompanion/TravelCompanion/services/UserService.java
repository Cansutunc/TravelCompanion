// Declare package for this file
package com.travelcompanion.TravelCompanion.services;

// Import necessary packages and classes

import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    public User updateUserHobbies(String email, String hobbie1, String hobbie2, String hobbie3, String hobbie4) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setHobbie1(hobbie1);
            user.setHobbie2(hobbie2);
            user.setHobbie3(hobbie3);
            user.setHobbie4(hobbie4);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found.");
        }
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
