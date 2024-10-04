package com.nishan.uberreviewservice.DTOs;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private String content;
    private Double rating;
    private Long booking;
    private Date createdAt;;
    private Date updatedAt;


}
