package com.mprribeiro.stockquotemanager.service.exception;

public class StockDoesntExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StockDoesntExistException(String msg) {
        super(msg);
    }

    public StockDoesntExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
