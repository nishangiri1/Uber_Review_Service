package com.nishan.uberreviewservice.adapters;

import com.entity.uberprojectentityservice.models.Review;
import com.nishan.uberreviewservice.DTOs.CreateReviewDto;


public interface ReviewDtoToReviewAdapter {
public Review convertDto(CreateReviewDto createReviewDto);
}
