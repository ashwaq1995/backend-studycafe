package com.studyCafeProject.Repository;

import com.studyCafeProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{

    @Query("select u From User u where u.email = ?1")
    User findByEmail(String email);

    @Query(value = "select * From User", nativeQuery=true)
    List<User> findAllUsers();

    @Query(value = "select * From User u where u.userId = ?1", nativeQuery=true)
    User getById(Long id);

    User findUserByUsername(String username);
}
