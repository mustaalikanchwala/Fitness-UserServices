package com.fitness.userServices.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String,String> fieldsErrors
) {
    public static ErrorResponse errorResponse(LocalDateTime time,int status , String error , String message , String path){
        return new ErrorResponse(
                time,
                status,
                error,
                message,
                path,
                null
        );
    }
    public static ErrorResponse errorResponseWithFields(LocalDateTime time,int status , String error , String message , String path , Map<String,String> errors){
        return new ErrorResponse(
                time,
                status,
                error,
                message,
                path,
                errors
        );
    }
}
