package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "bidlist")
public class BidList implements UpdatableModel<BidList> {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private Double bidQuantity;

    private Double askQuantity;

    private Double bid;

    private Double ask;

    private String benchmark;

    private Timestamp bidListDate;

    private String commentary;

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

    public BidList update(BidList model) {

        account = model.getAccount();
        type = model.getType();
        bidQuantity = model.getBidQuantity();
        askQuantity = model.getAskQuantity();
        bid = model.getBid();
        ask = model.getAsk();
        benchmark = model.getBenchmark();
        bidListDate = model.getBidListDate();
        commentary = model.getCommentary();

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
        return bidListId;
    }

}
