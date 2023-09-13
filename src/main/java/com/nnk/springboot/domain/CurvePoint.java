package com.nnk.springboot.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "curvepoint")
public class CurvePoint implements UpdatableModel<CurvePoint> {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer curveId;

    private Timestamp asOfDate;

    private Double term;

    @Column(name = "`VALUE`")
    private Double value;

    private Timestamp creationDate;

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
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
