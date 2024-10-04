package com.nishan.uberreviewservice.services;
import com.nishan.uberreviewservice.Exceptions.ResourceNotFound;
import com.nishan.uberreviewservice.Exceptions.ReviewNotFoundException;
import com.entity.uberprojectentityservice.models.Review;
import com.nishan.uberreviewservice.repositories.BookingRepository;
import com.nishan.uberreviewservice.repositories.DriverRepository;
import com.nishan.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;


    ReviewServiceImpl(ReviewRepository reviewRepository)
    {
        this.reviewRepository=reviewRepository;
    }

    @Override
    public Review findReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(
                        ()->new ReviewNotFoundException("Review not found with id "+id)
                );
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            reviewRepository.deleteById(id);
            return true;
        }
     catch (Exception e) {
         return false;
     }
    }

    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }


        @Override
        public Review updateReview(Long id, Review newReview) {

        int rowsAffected=reviewRepository.updateReviewById(
                id, newReview.getRating(), newReview.getContent()
        );
        if(rowsAffected==0)
        {
            throw new ReviewNotFoundException("Review with id " + id + " not found");
        }
        return findReviewById(id);
        }





}