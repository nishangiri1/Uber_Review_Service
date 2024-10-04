package com.nishan.uberreviewservice.controllers;

import com.nishan.uberreviewservice.DTOs.CreateReviewDto;
import com.nishan.uberreviewservice.DTOs.ReviewDto;
import com.nishan.uberreviewservice.adapters.ReviewDtoToReviewAdapter;
import com.nishan.uberreviewservice.services.ReviewService;
import org.springframework.http.HttpStatus;
import com.entity.uberprojectentityservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewDtoToReviewAdapter reviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, ReviewDtoToReviewAdapter reviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.reviewDtoToReviewAdapter = reviewDtoToReviewAdapter;
    }
    @PostMapping
    public ResponseEntity<?> publishReview(@Validated @RequestBody CreateReviewDto req)
    {

        return Optional.ofNullable(reviewDtoToReviewAdapter.convertDto(req))
                .map(reviewService::publishReview)
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .content(review.getContent())
                        .booking(review.getBooking().getId())
                        .rating(review.getRating())
                        .createdAt(review.getCreatedAt())
                        .updatedAt(review.getUpdatedAt())
                        .build()
                )
                .map(response ->  new ResponseEntity<Object>(response,HttpStatus.CREATED))
                .orElseGet(()->  new ResponseEntity<>("Invalid argument",HttpStatus.BAD_REQUEST));


//        Review incommingReview=reviewDtoToReviewAdapter.convertDto(req);
//        if(incommingReview==null) {
//            return new ResponseEntity<>("Incomming invalid", HttpStatus.BAD_REQUEST);
//        }
//        Review review=reviewService.publishReview(incommingReview);
//        ReviewDto response=ReviewDto.builder()
//                .id(review.getId())
//                .content(review.getContent())
//                .booking(review.getBooking().getId())
//                .rating(review.getRating())
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews()
    {
        List<Review> review=reviewService.findAllReviews();
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId)
    {
       return new ResponseEntity<>(reviewService.findReviewById(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReviewById(@PathVariable Long reviewId,@RequestBody Review review)
    {
        return new ResponseEntity<>(reviewService.updateReview(reviewId,review),HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId)
    {
        return new ResponseEntity<>(reviewService.deleteReviewById(reviewId),HttpStatus.OK);
    }

}
