package com.nishan.uberreviewservice.adapters;

import com.entity.uberprojectentityservice.models.Booking;
import com.entity.uberprojectentityservice.models.Review;
import com.nishan.uberreviewservice.DTOs.CreateReviewDto;
import com.nishan.uberreviewservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CreateReviewDtoToReviewAdapterImpl implements ReviewDtoToReviewAdapter{

    private BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {
        Optional<Booking> booking=bookingRepository.findById(createReviewDto.getBookingId());
        return booking.map(value -> Review.builder()
                .rating(createReviewDto.getRating())
                .booking(value)
                .content(createReviewDto.getContent())
                .build()).orElse(null);
    }
}
