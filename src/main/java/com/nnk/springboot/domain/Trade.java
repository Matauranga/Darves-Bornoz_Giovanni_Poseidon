package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "trade")
public class Trade implements UpdatableModel<Trade> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    private String benchmark;

    private Timestamp tradeDate;

    private String security;

    private String status;

    private String trader;

    private String book;

    private String creationName;

    private Timestamp creationDate;

    private String revisionName;

    private Timestamp revisionDate;

    private String dealName;

    private String dealType;

    private String sourceListId;

    private String side;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    public Trade update(Trade model) {

        account = model.getAccount();
        type = model.getType();
        buyQuantity = model.getBuyQuantity();
        sellQuantity = model.getSellPrice();
        buyPrice = model.getBuyPrice();
        sellPrice = model.getSellPrice();
        benchmark = model.getBenchmark();
        tradeDate = model.getTradeDate();

        security = model.getSecurity();
        status = model.getStatus();
        trader = model.getTrader();
        book = model.getBook();
        revisionName = model.getRevisionName();
        revisionDate = model.getRevisionDate();
        dealName = model.getDealName();
        dealType = model.getDealType();
        sourceListId = model.getSourceListId();
        side = model.getSide();

        return this;
    }

    public Integer getId() {
        return tradeId;
    }

}
