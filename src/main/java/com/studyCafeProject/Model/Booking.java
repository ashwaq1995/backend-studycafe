package com.studyCafeProject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "booking date is required")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date bookingDate;

    @NotNull(message = "persons is required")
    private Integer personsCount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(insertable = false, updatable = false)
    private User user;

    @NotNull(message = "userId is required")
    @Column(name = "user_id")
    private Integer userId;

    @OneToOne
    @JoinColumn(insertable = false, updatable = false)
    @JsonIgnore
    private Room room;

    @NotNull(message = "roomId is required")
    @Column(name = "room_id")
    private Integer roomId;

}
