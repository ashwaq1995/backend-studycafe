package com.studyCafeProject.Controller;

import com.studyCafeProject.DTO.Api;
import com.studyCafeProject.Model.Booking;
import com.studyCafeProject.Model.Review;
import com.studyCafeProject.Service.CafeService;
import com.studyCafeProject.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    Logger logger = LoggerFactory.getLogger(BookingController.class);

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getReviews() {
        return ResponseEntity.status(200).body(reviewService.getReviews());
    }

    //add review
    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody Review review){
        logger.info("add new review");
        reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("Review added !",201));
    }

    @GetMapping("/by-cafe-id/{cafeId}")
    public ResponseEntity<List<Review>> getAllCafeReviews(@PathVariable("cafeId") Integer cafeId){
        return  ResponseEntity.status(200).body(reviewService.findAllByCafeId(cafeId));
    }


    @GetMapping("/get-rate-five")
    public ResponseEntity<List<Review>> getFiveStar(){
        List<Review> reviews = reviewService.getFiveStar();
        return ResponseEntity.status(200).body(reviews);
    }



}
