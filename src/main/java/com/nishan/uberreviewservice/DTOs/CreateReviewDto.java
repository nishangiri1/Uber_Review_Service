package com.nishan.uberreviewservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDto {
    private String content;
    private Double rating;
    private Long bookingId;
}
