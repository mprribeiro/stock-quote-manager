package com.mprribeiro.stockquotemanager.service;

import com.mprribeiro.stockquotemanager.domain.StockQuote;
import com.mprribeiro.stockquotemanager.repository.StockQuoteRepository;
import com.mprribeiro.stockquotemanager.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StockQuoteService {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    public StockQuote find(String id) {
        Optional<StockQuote> obj = Optional.ofNullable(stockQuoteRepository.getByStock(id));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
    }

    public List<StockQuote> findAll() {
        return stockQuoteRepository.findAll();
    }

    public StockQuote insert(StockQuote stockQuote) {
        try {
            StockQuote stockQuote1 = find(stockQuote.getId());
            stockQuote1.addQuotes(stockQuote.getQuotes());
            return stockQuoteRepository.save(stockQuote1);
        } catch (ObjectNotFoundException e ) {
            return stockQuoteRepository.save(stockQuote);
        }
    }
}
