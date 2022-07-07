package com.studyCafeProject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotNull(message = "image is required")
    @URL(message = "image must be a URL")
    private String image;

    @NotEmpty(message = "location is required")
    private String location;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private Set<Review> reviews;

//    @ManyToMany
//    private Set<User> user;

}
