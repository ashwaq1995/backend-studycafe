package com.studyCafeProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    @JsonIgnore
    private Cafe cafe;

    @Column(name = "cafe_id")
    @NotNull(message = "cafeId is required")
    private Integer cafeId;

    @NotEmpty(message = "message is required")
    @Size(min = 3, message = "message have to be 6 length long")
    private String message;

    @NotNull(message = "rate is required")
    @Range(min = 0, max = 5, message = "rate must be a number between 0 - 5")
    private Integer rate = 0;
}
