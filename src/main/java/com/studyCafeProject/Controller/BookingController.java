package com.studyCafeProject.Controller;

import com.studyCafeProject.DTO.Api;
import com.studyCafeProject.Model.Booking;
import com.studyCafeProject.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    Logger logger = LoggerFactory.getLogger(BookingController.class);

    //get all bookings
    @GetMapping("/all/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        logger.info("get all bookings");
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    //add booking
    @PostMapping("/add/booking")
    public ResponseEntity addBooking(@RequestBody Booking booking){
        logger.info("add new booking");
        bookingService.addBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("Booking added !",201));
    }

    //Get booking by id
    @GetMapping("/check/booking/{id}")
    public ResponseEntity<Api> checkBookingIsValid(@PathVariable Integer id){
        bookingService.checkBookingIsValid(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Valid booking",200));
    }

    //update booking
    @PutMapping("/update/booking")
    public ResponseEntity updateBooking(@RequestBody Booking booking) {
        bookingService.updateBooking(booking);
        return ResponseEntity.status(200).body("Booking updated! ");
    }

    //delete booking
    @DeleteMapping("/delete/booking/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.status(200).body("Booking deleted! ");
    }

}
