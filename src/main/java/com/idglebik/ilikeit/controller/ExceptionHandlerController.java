package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.exception.CantRemoveUserException;
import com.idglebik.ilikeit.exception.CantSaveUserException;
import com.idglebik.ilikeit.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


@ControllerAdvice
@RestController
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        LOGGER.log(Level.SEVERE, "Exception was handled", ex);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CantSaveUserException.class, CantRemoveUserException.class})
    public final ResponseEntity<ExceptionResponse> handleUserBadRequestException(CantSaveUserException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        LOGGER.log(Level.WARNING,"Error", exceptionResponse);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
