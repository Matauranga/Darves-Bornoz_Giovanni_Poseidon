package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating implements UpdatableModel<Rating> {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String moodysRating;

    private String sandPRating;

    private String fitchRating;

    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating update(Rating model) {

        moodysRating = model.getMoodysRating();
        sandPRating = model.getSandPRating();
        fitchRating = model.getFitchRating();
        orderNumber = model.getOrderNumber();

        return this;
    }


}
