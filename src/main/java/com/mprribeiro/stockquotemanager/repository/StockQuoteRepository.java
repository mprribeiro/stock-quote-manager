package com.mprribeiro.stockquotemanager.repository;

import com.mprribeiro.stockquotemanager.domain.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockQuoteRepository extends JpaRepository<StockQuote, String> {

    @Query(value = "SELECT id, date, value FROM StockQuote s JOIN quotes q WHERE q.quote_id = s.id AND q.quote_id LIKE CONCAT('%',:id, '%')", nativeQuery = true)
    public StockQuote getByStock(@Param("id") String id);
}
