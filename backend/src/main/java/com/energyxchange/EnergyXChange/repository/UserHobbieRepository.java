package com.energyxchange.EnergyXChange.repository;

import com.energyxchange.EnergyXChange.model.Routes;
import com.energyxchange.EnergyXChange.model.UserHobbie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Enable JPA repositories and mark this interface as a repository
@EnableJpaRepositories
@Repository
public interface UserHobbieRepository extends JpaRepository<UserHobbie, Integer> {
    


//    @Transactional
//    @Query("SELECT t FROM Routes t WHERE t.kkk = :userID")
//    List<Routes> findByUser(@Param("userID") int userID);

    UserHobbie findByFirstUserIdAndSecondUserId(int user1Id,int user2Id);

    List<UserHobbie> findByFirstUserIdOrderByTotalDesc(int id);

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
    @Modifying
    @Transactional
    @Query("UPDATE UserHobbie t SET t.total = :total " +
            "WHERE t.id = :id")
    void updateUserHobbieById(@Param("id") int id,@Param("total") int total);

//    Query method to modify the id of the buyer given timestamp and sellerID
//    @Modifying
//    @Transactional
//    @Query("UPDATE Routes t SET t.city = :city,t.country = :country " +
//            "WHERE t.userID = :userId")
//    void updateUserRoute( @Param("timestamp") String  timestamp, @Param("userId") int userId,
//                        @Param("city") String  city, @Param("country") String country);
}
