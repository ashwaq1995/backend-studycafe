package com.studyCafeProject.Repository;

import com.studyCafeProject.Model.Cafe;
import com.studyCafeProject.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {

    Optional<Cafe> findCafeById(Integer id);

    List<Cafe> findAllByName(@Param("name") String name);

    @Query("select  c from Cafe c join Review r on r.cafe.id = c.id where r.rate =:rate")
    List<Cafe> findAllByRate(@Param("rate") Integer rate);

}
