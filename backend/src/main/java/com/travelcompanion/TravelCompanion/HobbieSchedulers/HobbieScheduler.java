package com.travelcompanion.TravelCompanion.HobbieSchedulers;

import java.util.List;

import com.travelcompanion.TravelCompanion.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.RabbitMQ.QueueSender;

@Configuration
@EnableScheduling
public class HobbieScheduler {
    private final UserRepository userRepository;
    private final QueueSender queueSender;


    public HobbieScheduler( UserRepository userRepository,QueueSender queueSender) {
        this.userRepository = userRepository;
        this.queueSender = queueSender;
    }

    @Scheduled(fixedDelay = 10000)
    public void simulateEnergy(){
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            queueSender.send(user);

        });
    }

}
