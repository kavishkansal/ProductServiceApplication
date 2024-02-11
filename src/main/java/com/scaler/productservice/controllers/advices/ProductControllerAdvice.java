package com.scaler.productservice.controllers.advices;

import com.scaler.productservice.controllers.ProductController;
import com.scaler.productservice.dtos.ExceptionDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
//@ControllerAdvice(assignableTypes = ProductController.class)
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");
        return exceptionDto;
    }
}
