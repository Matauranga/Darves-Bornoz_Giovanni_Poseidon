package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName implements UpdatableModel<RuleName> {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private String json;

    private String template;

    private String sqlStr;

    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public RuleName update(RuleName model) {

        name = model.getName();
        description = model.getDescription();
        json = model.getJson();
        template = model.getTemplate();
        sqlStr = model.getSqlStr();
        sqlPart = model.getSqlPart();

        return this;
    }

}
