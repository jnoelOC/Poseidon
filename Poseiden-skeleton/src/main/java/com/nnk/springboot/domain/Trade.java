package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="TRADEID", unique=true, nullable=false)
    private Integer tradeId;
    @Column(name="ACCOUNT", nullable=false, length=30)
    private String account;
    @Column(name="TYPE", nullable=false, length=30)
    private String type;
    @Column(name="BUYQUANTITY")
    private Double buyQuantity;
    @Column(name="SELLQUANTITY")
    private Double sellQuantity;
    @Column(name="BUYPRICE")
    private Double buyPrice;
    @Column(name="SELLPRICE")
    private Double sellPrice;
    @Column(name="BENCHMARK", length=125)
    private String benchmark;
    @Column(name="TRADEDATE")
    private Timestamp tradeDate;
    @Column(name="SECURITY", length=125)
    private String security;
    @Column(name="STATUS", length=10)
    private String status;
    @Column(name="TRADER", length=125)
    private String trader;
    @Column(name="BOOK", length=125)
    private String book;
    @Column(name="CREATIONNAME", length=125)
    private String creationName;
    @Column(name="CREATIONDATE")
    private Timestamp creationDate;
    @Column(name="REVISIONNAME", length=125)
    private String revisionName;
    @Column(name="REVISIONDATE")
    private Timestamp revisionDate;
    @Column(name="DEALNAME", length=125)
    private String dealName;
    @Column(name="DEALTYPE", length=125)
    private String dealType;
    @Column(name="SOURCELISTID", length=125)
    private String sourceListId;
    @Column(name="SIDE", length=125)
    private String side;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
