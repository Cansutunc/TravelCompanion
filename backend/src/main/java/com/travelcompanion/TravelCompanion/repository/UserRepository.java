package com.travelcompanion.TravelCompanion.repository;

import java.util.List;

import com.travelcompanion.TravelCompanion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

// Import the User and JpaRepository classes


// Enable JPA repositories and mark this interface as a repository
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Define method to find a User by email and password
    List<User> findByEmailAndPassword(String email, String password);

    // Define method to find a User by email
    User findByEmail(String email);
    
    // Define method to find a User by id
    User findByid(int id);

    List<User> findAll();

    //Query database for User with least energy
//    @Query("SELECT b FROM User b WHERE b.batteryCapacity = (SELECT MIN(b.batteryCapacity) FROM User b)")
//    public List<User> findUsersWithLeastEnergy();
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE User s SET s.batteryCapacity = :batteryCapacity WHERE s.id = :id")
//    void setBatteryCapacity(@Param("batteryCapacity") float battery_capacity, @Param("id") int id);
//
//    @Query("SELECT b.batteryCapacity FROM User b WHERE b.id = :id")
//    float getBatteryCapacity(@Param("id") int id);

}
