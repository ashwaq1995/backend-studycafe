package com.studyCafeProject.Service;

import com.studyCafeProject.Exception.InvalidIdException;
import com.studyCafeProject.Model.Booking;
import com.studyCafeProject.Repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
         return bookingRepository.findAll();
    }

    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public void checkBookingIsValid(Integer id) {
        Booking mybooking = bookingRepository.findById(id)
                .orElseThrow(
                        ()->new InvalidIdException("Invalid id"));

    }

    //update booking
    public void updateBooking( Booking booking) {
        bookingRepository.save(booking);
    }

    public boolean deleteBooking(Integer id){
        Optional<Booking> mBooking = bookingRepository.findById(id);
        if (!mBooking.isPresent()){
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }

}
