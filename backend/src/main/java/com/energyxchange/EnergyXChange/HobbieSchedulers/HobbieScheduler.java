package com.energyxchange.EnergyXChange.HobbieSchedulers;

import java.util.List;

import com.energyxchange.EnergyXChange.repository.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import com.energyxchange.EnergyXChange.model.User;
import com.energyxchange.EnergyXChange.RabbitMQ.QueueSender;
import com.energyxchange.EnergyXChange.controller.TimeController;
import com.energyxchange.EnergyXChange.repository.RouteRepository;

@Configuration
@EnableScheduling
public class HobbieScheduler {
    private final UserRepository userRepository;
    private final QueueSender queueSender;


    public HobbieScheduler( UserRepository userRepository,QueueSender queueSender) {
        this.userRepository = userRepository;
        this.queueSender = queueSender;
    }

    @Scheduled(fixedDelay = 5000)
    public void simulateEnergy(){
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            queueSender.send(user);

        });
    }

}
