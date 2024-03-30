package com.travelcompanion.TravelCompanion.RabbitMQ;

import com.travelcompanion.TravelCompanion.model.UserHobbie;
import com.travelcompanion.TravelCompanion.repository.UserHobbieRepository;
import com.travelcompanion.TravelCompanion.services.UserService;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import com.travelcompanion.TravelCompanion.controller.UserController;
import com.travelcompanion.TravelCompanion.model.User;
import com.travelcompanion.TravelCompanion.repository.UserRepository;
import com.travelcompanion.TravelCompanion.repository.RouteRepository;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

@Component
public class QueueConsumer {

    private final UserRepository userRepository;
    private final UserController userController;
    private final RouteRepository routeRepository;
    private final UserHobbieRepository userHobbieRepository;

    private final UserService userService;

    public QueueConsumer(RouteRepository routeRepository, UserRepository userRepository, UserController userController,UserHobbieRepository userHobbieRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userController = userController;
        this.routeRepository = routeRepository;
        this.userHobbieRepository = userHobbieRepository;
        this.userService = userService;
    }

    //TODO: rimuovere?
    public Jackson2JsonMessageConverter consumerJsonConverter(){
        return new Jackson2JsonMessageConverter();
    }
    
    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload CustomMessage fileBody) {

        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            if (user.getId() != fileBody.getUser().getId()){
                int total = userService.Calculate(user,fileBody.getUser());
                UserHobbie existsData = userHobbieRepository.findByFirstUserIdAndSecondUserId(user.getId(),fileBody.getUser().getId());
                if (existsData == null){
                    var userHobbie = UserHobbie.builder()
                            .firstUserId(user.getId())
                            .secondUserId(fileBody.getUser().getId())
                            .total(total)
                            .build();
                    userHobbieRepository.save(userHobbie);
                }else {

                    if(total != existsData.getTotal()){
                        userHobbieRepository.updateUserHobbieById(existsData.getId(),total);
                    }

                }
//

            }
        }
//
//        Random rand = new Random();
//        User leastBatteryUser = leastBatteryUsers.get(rand.nextInt(leastBatteryUsers.size()));
//
//        // Call UserController method to increment battery capacity
//        userController.buyEnergy(leastBatteryUser.getId(), fileBody.getAmountOfEnergy(), fileBody.getSellerID());
//
//        System.out.println("Sold " + String.valueOf(fileBody.getAmountOfEnergy()) + "energy from seller "
//                + String.valueOf(fileBody.getSellerID()) + " to user " + String.valueOf(leastBatteryUser.getId()));
//
//        double price = fileBody.getAmountOfEnergy() * 0.15;
//
//        //round price to int
//        int rounded_price = (int) Math.ceil(price);
//
//        //add route
//        routeRepository.addRoute(  fileBody.getTimestamp(), fileBody.getAmountOfEnergy(),
//                                                rounded_price, leastBatteryUser.getId(), fileBody.getSellerID());
//
//    }

    }

}
