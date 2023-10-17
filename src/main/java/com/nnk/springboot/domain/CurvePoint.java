package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Must not be null")
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
