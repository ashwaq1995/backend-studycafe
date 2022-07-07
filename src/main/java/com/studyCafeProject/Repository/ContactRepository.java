package com.studyCafeProject.Repository;

import com.studyCafeProject.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query("select c from Contact c where c.User.id=?1")
    List<Contact> findAllByUser(String userId);
}
