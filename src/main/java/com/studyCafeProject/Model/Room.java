package com.studyCafeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "roomNo is required")
    @Column(name = "room_No")
    private Integer roomNo;

    @NotNull(message = "price is required")
    @Positive(message = "price must be positive")
    private Integer price;

    @NotNull(message = "capacity is required")
    private Integer capacity;

    @NotNull(message = "isAvailable is required")
    private Boolean isAvailable = true;

    @OneToOne(mappedBy = "room")
    @JsonIgnore
    private Booking booking;


    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    @JsonIgnore
    private Cafe cafe;

    @NotNull(message = "cafeId is required")
    @Column(name = "cafe_id")
    private Integer cafeId;

}
