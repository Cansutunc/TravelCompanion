package com.travelcompanion.TravelCompanion.repository;

import com.travelcompanion.TravelCompanion.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

// Enable JPA repositories and mark this interface as a repository
@EnableJpaRepositories
@Repository
public interface RouteRepository extends JpaRepository<Routes, Integer> {
    
    // Define method to find a trransaction by its id
    Routes findByid(int id);

    List<Routes> findAll();
    Routes findRoutesByUserIdAndCity(int id,String city);
    
    // Query method to find all transactions by the seller
//    @Transactional
//    @Query("SELECT t FROM Routes t WHERE t.kkk = :userID")
//    List<Routes> findByUser(@Param("userID") int userID);



//    @Transactional
//    @Query("SELECT t FROM Routes t WHERE t.city = :city")
//    Routes findByCity(@Param("city") String city);




    // Query method to find all transactions by the buyer

    // Query method to sum the revenue from all transactions with seller id
//    @Transactional
//    @Query("SELECT SUM(t.price) FROM Routes t WHERE t.sellerID = :sellerID")
//    int getSellerRevenue(@Param("sellerID") int sellerID);

    // Query method to sum the energy sold from all transactions with seller id
//    @Transactional
//    @Query("SELECT SUM(t.amount) FROM Routes t WHERE t.sellerID = :sellerID")
//    int getSellerTotalEnergySold(@Param("sellerID") int sellerID);

    // Query method to sum the expenses from all transactions with buyer id
//    @Transactional
//    @Query("SELECT SUM(t.price) FROM Routes t WHERE t.buyerID = :buyerID")
//    int getBuyerTotalExpenses(@Param("buyerID") int buyerID);

    // Query method to sum the energy buyed from all transactions with buyer id
//    @Transactional
//    @Query("SELECT SUM(t.amount) FROM Routes t WHERE t.buyerID = :buyerID")
//    int getBuyerTotalEnergyBuyed(@Param("buyerID") int buyerID);

    // Query method to add a transaction
//    @Modifying
//    @Transactional
//    @Query("INSERT INTO Route (timestamp, amount, price, buyerID, sellerID) \n" +
//                    "VALUES (:timestamp, :amount, :price, :buyerID, :sellerID)")
//    void addRoute(@Param("timestamp") int timestamp, @Param("amount") float amount, @Param("price") int price,
//                        @Param("buyerID") int buyerID, @Param("sellerID") int sellerID);

    //Query method to modify the id of the buyer given timestamp and sellerID
//    @Modifying
//    @Transactional
//    @Query("UPDATE Routes t SET t.buyerID = :buyerID " +
//            "WHERE t.timestamp = :timestamp AND t.sellerID = :sellerID AND t.amount = :amount")
//    void modifyBuyerID( @Param("timestamp") int timestamp, @Param("buyerID") int buyerID,
//                        @Param("sellerID") int sellerID, @Param("amount") float amount);

//    Query method to modify the id of the buyer given timestamp and sellerID
//    @Modifying
//    @Transactional
//    @Query("UPDATE Routes t SET t.city = :city,t.country = :country " +
//            "WHERE t.userID = :userId")
//    void updateUserRoute( @Param("timestamp") String  timestamp, @Param("userId") int userId,
//                        @Param("city") String  city, @Param("country") String country);
}
