package com.mprribeiro.stockquotemanager.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mprribeiro.stockquotemanager.domain.Stock;
import com.mprribeiro.stockquotemanager.domain.StockQuote;
import com.mprribeiro.stockquotemanager.service.StockQuoteService;
import com.mprribeiro.stockquotemanager.service.exception.StockDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/stock-quotes")
public class StockQuoteController {

    @Autowired
    private StockQuoteService stockQuoteService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value="/{id}")
    public ResponseEntity<StockQuote> find(@PathVariable String id) {
        StockQuote stockQuote = stockQuoteService.find(id);
        return ResponseEntity.ok(stockQuote);
    }

    @GetMapping()
    public ResponseEntity<List<StockQuote>> findAll() {
        List<StockQuote> list = stockQuoteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping()
    public ResponseEntity<StockQuote> insert(@RequestBody String json) {
        JsonElement root = new JsonParser().parse(json);
        String id = root.getAsJsonObject().get("id").toString();
        id = id.substring(1, id.length() - 1);
        JsonObject object = root.getAsJsonObject().get("quotes").getAsJsonObject();
        Map<String, String> map = new Gson().fromJson(object.toString(), Map.class);

        Stock stock = restTemplate.getForObject("http://localhost:8080/stock/" + id, Stock.class);
        StockQuote stockQuote = new StockQuote();

        if (stock != null) {
            stockQuote.setId(id);
            stockQuote.addQuotes(map);
            stockQuote = stockQuoteService.insert(stockQuote);
        }
        else {
            throw new StockDoesntExistException("Stock " + id + " doesn't exist.");
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(stockQuote.getId()).toUri();
        return ResponseEntity.created(uri).body(stockQuote);
    }
}
