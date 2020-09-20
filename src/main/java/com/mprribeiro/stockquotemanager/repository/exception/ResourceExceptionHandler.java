package com.mprribeiro.stockquotemanager.repository.exception;

import com.mprribeiro.stockquotemanager.service.exception.ObjectNotFoundException;
import com.mprribeiro.stockquotemanager.service.exception.StockDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request)
            throws ParseException {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
        Date date = new Date(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        StandardError err = new StandardError(sdf.format(date), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(StockDoesntExistException.class)
    public ResponseEntity<StandardError> stockDoesntExist(StockDoesntExistException e, HttpServletRequest request)
            throws ParseException {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
        Date date = new Date(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        StandardError err = new StandardError(sdf.format(date), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
