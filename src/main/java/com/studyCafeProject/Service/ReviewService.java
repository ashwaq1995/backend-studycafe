package com.studyCafeProject.Service;

import com.studyCafeProject.Model.Review;
import com.studyCafeProject.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> findAllByCafeId(Integer cafeId) {
        return reviewRepository.findAllByCafeId(cafeId);
    }

    //to get rate
    public List<Review> getFiveStar() {
        return reviewRepository.findAllFiveStar();
    }

}


