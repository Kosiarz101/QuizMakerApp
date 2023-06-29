package com.QuizMaker.QuizMakerApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandlerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(value = { ResponseStatusException.class })
    protected ResponseStatusException handleConflict(ResponseStatusException ex, HttpServletRequest httpRequest) {

        LOGGER.info("{} at {} ", ex.getMessage(), httpRequest.getRequestURI());
        return ex;
    }
}
