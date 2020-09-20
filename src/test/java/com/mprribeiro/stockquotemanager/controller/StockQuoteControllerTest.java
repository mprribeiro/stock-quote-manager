package com.mprribeiro.stockquotemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mprribeiro.stockquotemanager.domain.StockQuote;
import com.mprribeiro.stockquotemanager.service.StockQuoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = StockQuoteController.class)
@AutoConfigureMockMvc
public class StockQuoteControllerTest {

    static String STOCK_API = "/stock_quotes";

    @Autowired
    MockMvc mvc;

    @MockBean
    StockQuoteService service;

    @Test
    @DisplayName("Deve criar um novo registro")
    public void createStockQuote() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("2020-10-11", "10");
        map.put("2020-10-12", "12");
        StockQuote stockQuote = new StockQuote("petr3", map);

        Map<String, String> map2 = new HashMap<>();
        map.put("2020-10-21", "13");
        map.put("2020-10-22", "18");
        StockQuote stckQuote = new StockQuote("petr5", map);

        BDDMockito.given(service.insert(Mockito.any(StockQuote.class))).willReturn(stockQuote);
        String json = new ObjectMapper().writeValueAsString(stckQuote);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(STOCK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("quote").isNotEmpty());

    }
}
