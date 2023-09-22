package com.nnk.springboot.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "curvepoint")
public class CurvePoint implements UpdatableModel<CurvePoint> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer curveId;

    private Timestamp asOfDate;

    private Double term;

    @Column(name = "`VALUE`")
    private Double value;

    private Timestamp creationDate;

    public CurvePoint(Double term, Double value) { //TODO pk avant CurveID et pk curveID tout court
        this.term = term;
        this.value = value;
    }

    public CurvePoint update(CurvePoint model) {

        curveId = model.getCurveId();
        asOfDate = model.getAsOfDate();
        term = model.getTerm();
        value = model.getValue();

        return this;
    }


}
