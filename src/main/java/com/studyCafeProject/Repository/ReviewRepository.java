package com.studyCafeProject.Repository;

import com.studyCafeProject.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select r from Review r WHERE r.rate = 5")
    List<Review> findAllFiveStar();

    @Query("select r from Review r where r.cafe.id=:cafeId")
    List<Review> findAllByCafeId(@Param("cafeId") Integer cafeId);
}
