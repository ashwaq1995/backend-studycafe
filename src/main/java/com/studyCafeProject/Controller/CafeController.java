package com.studyCafeProject.Controller;

import com.studyCafeProject.Model.Cafe;
import com.studyCafeProject.Service.CafeService;
import com.studyCafeProject.Service.ReviewService;
import com.studyCafeProject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cafe")
public class CafeController {

    private final CafeService cafeService;
    private final ReviewService reviewService;
    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(CafeController.class);


    // Get All Cafe
    @GetMapping("/all")
    public ResponseEntity<List<Cafe>> getAllCafe() {
        logger.info("get all cafes");
        List<Cafe> cafes = cafeService.getAllCafe();
        return ResponseEntity.status(HttpStatus.OK).body(cafes);
    }


    // Add Cafe
    @PostMapping("/add/cafe")
    public ResponseEntity addCafe(@RequestBody Cafe cafe) {
        logger.info("add cafe");
        cafeService.addCafe(cafe);
        return ResponseEntity.status(200).body("Cafe Added !");
    }


    // Get Cafe By id
    @GetMapping("/{id}")
    public ResponseEntity<Cafe> getCafeById(@PathVariable("id") Integer id) {
        logger.info("get cafe by id");
        Cafe cafe = cafeService.getCafeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cafe);
    }


    //get Cafe by name
    @GetMapping("/name")
    public ResponseEntity<List<Cafe>> getCafeByName(@PathVariable("name") String name) {
        logger.info("get cafe by name");
        List<Cafe> cafe = cafeService.getCafeByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(cafe);
    }

    // Get Cafe By Rating
    @GetMapping("/rate")
    public ResponseEntity<List<Cafe>> getCafeByRating(@PathVariable Integer rate) {
        logger.info("get cafe by rating");
        List<Cafe> cafe = cafeService.getCafeByRating(rate);
        return ResponseEntity.status(HttpStatus.OK).body(cafe);
    }



//    @PostMapping("/review/{userid}/{cafeid}")
//    public ResponseEntity<Object> addReviewByCafeIdAndUserId(@PathVariable String userid, @PathVariable String cafeid,@RequestBody @Valid Review review, Errors errors){
//
//        if(errors.hasErrors()){
//            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
//        }
//        int isReviewAdded = userService.addReviewByCafeIdAndUserId(userid,cafeid,review);
//
//        return switch (isReviewAdded) {
//            case -1 -> ResponseEntity.status(400).body("User id is wrong");
//            case 0 -> ResponseEntity.status(200).body("Add review completed");
//            case 1 -> ResponseEntity.status(400).body("Cafe id is wrong");
//            default -> ResponseEntity.status(500).body("server error");
//        };
//    }


    //update cafe
    @PutMapping("/update/cafe")
    public ResponseEntity updateCafe(@RequestBody Cafe cafe) {
        cafeService.updateCafe(cafe);
        return ResponseEntity.status(200).body("Cafe updated! ");
    }

    //delete cafe
    @DeleteMapping("/delete/cafe/{id}")
    public ResponseEntity deleteCafe(@PathVariable Integer id) {
        cafeService.deleteCafe(id);
        return ResponseEntity.status(200).body("Cafe deleted! ");
    }


}
