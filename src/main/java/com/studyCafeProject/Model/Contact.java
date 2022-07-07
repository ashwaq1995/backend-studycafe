package com.studyCafeProject.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Contact  {
    @Id
    private String id = UUID.randomUUID().toString().toUpperCase();

    @NotNull(message = "Username is required")
    private String name;

    @NotNull(message = "Phone Number is required")
    private String phoneNo;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User User;
}
