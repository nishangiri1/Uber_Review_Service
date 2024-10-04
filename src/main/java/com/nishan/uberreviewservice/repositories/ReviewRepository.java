package com.nishan.uberreviewservice.repositories;
import com.entity.uberprojectentityservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Integer countAllByRatingIsLessThanEqual(Double rating);
    List<Review> findAllByRatingIsLessThanEqual(Double rating);

    List<Review> findAllByCreatedAtBefore(Date createdAt);

    @Query("select r from Booking b inner join Review r where b.id= :bookingId")
    Review findReviewByBookingId(Long bookingId);
    boolean findReviewById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.rating = :rating, r.content = :content, r.updatedAt = CURRENT_TIMESTAMP WHERE r.id = :id")
    int updateReviewById(Long id, Double rating, String content);

    int deleteReviewById(Long id);

}



























//    @Query("SELECT new com.nishan.uberreviewservice.repositories(r,p,d) from Booking b inner join Passenger p inner join Driver d")
//    Customer findDriverPassangerReviewByBookingId(Long bookingId);

//class Customer{
//    public Review review;
//    public Passenger passenger;
//    public Driver driver;
//
//    public Customer(Review review, Passenger passenger, Driver driver) {
//        this.review = review;
//        this.passenger = passenger;
//        this.driver = driver;
//    }
//}
