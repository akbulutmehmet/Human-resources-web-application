package com.bm470.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class AppExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFound(HttpServletRequest request,Exception e){

        logger.error(request.getRequestURL() + " istegine karsilik karsilayici bulunamadi. " +
                "Hata mesaji: " + e.getMessage());
        return "404";
    }

    @ExceptionHandler(value = {NullPointerException.class,ServletException.class,ClassNotFoundException.class, SQLException.class,IOException.class
    , NoResultException.class
    })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalError(HttpServletRequest request,Exception e){
        logger.error(request.getRequestURL() + " istegine karsilik karsilayici bulunamadi. " +
                "Hata mesaji: " + e.getMessage());
        return "500";
    }

}
