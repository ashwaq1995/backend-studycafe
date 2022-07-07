package com.studyCafeProject.Service;

import com.studyCafeProject.Exception.CafeException;
import com.studyCafeProject.Model.Cafe;
import com.studyCafeProject.Model.Review;
import com.studyCafeProject.Repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    public List<Cafe> getAllCafe() {
        return cafeRepository.findAll();
    }


    public void addCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }



    //get cafe by id
    public Cafe getCafeById(Integer id) {
        return cafeRepository.findCafeById(id)
                .orElseThrow(() ->
                        new CafeException(String.format("Cafe with id %s couldn't be found", id)));

    }


    //get Cafe by name
    public List<Cafe> getCafeByName(String name) {
        return cafeRepository.findAllByName(name);

    }

    //to allow user to search on cafe by the rating 5
    public List<Cafe> getCafeByRating(Integer rate) {
        return cafeRepository.findAllByRate(rate);


    }

    //get all reviews by cafe id
    public Set<Review> getAllReviews(Integer cafeId) {
        Cafe cafe =  cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeException("No Cafes found with this id > " + cafeId));
        return cafe.getReviews();
    }


    //update cafe
    public void updateCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    //delete cafe
    public boolean deleteCafe(Integer id){
        Optional<Cafe> cc = cafeRepository.findById(id);
        if (!cc.isPresent()){
            return false;
        }
        cafeRepository.deleteById(id);
        return true;
    }

}
