package com.mprribeiro.stockquotemanager.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class StockQuote implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @ElementCollection
    @MapKeyColumn(name="date")
    @Column(name="value")
    @CollectionTable(name="quotes", joinColumns=@JoinColumn(name="quote_id"))
    private Map<String, String> quotes;

    public StockQuote() {
        this.quotes = new HashMap<>();
    }

    public StockQuote(String id, Map<String, String> quotes) {
        this.id = id;
        this.quotes = quotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void addQuotes(Map<String, String> quotes) {
        quotes.forEach((key, value) -> this.quotes.put(key, value));
    }
}
