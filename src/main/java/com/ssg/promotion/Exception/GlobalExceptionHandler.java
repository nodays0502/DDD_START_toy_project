package com.ssg.promotion.Exception;

import static com.ssg.promotion.common.dto.ResponseMessage.*;

import com.ssg.promotion.Exception.product.NotFoundProductException;
import com.ssg.promotion.Exception.promotion.NotFoundPromotionException;
import com.ssg.promotion.Exception.user.AlreadyUserStateIsResignException;
import com.ssg.promotion.Exception.user.NotFoundUserException;
import com.ssg.promotion.Exception.user.ResignedUserException;
import com.ssg.promotion.common.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        System.out.println("잘못된 입력 에러");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ResponseDto(ResponseDto.FAIL, BAD_REQUEST_MESSAGE));
    }

    @ExceptionHandler(AlreadyUserStateIsResignException.class)
    public ResponseEntity handleAlreadyUserStateIsResignException(
        AlreadyUserStateIsResignException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ResponseDto(ResponseDto.FAIL, ALREADY_RESIGNED_MESSAGE));
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity handleNotFoundUserException(
        NotFoundUserException ex) {
        System.out.println("유저 없는 에러");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseDto(ResponseDto.FAIL, NOT_FOUND_USER_MESSAGE));
    }

    @ExceptionHandler(ResignedUserException.class)
    public ResponseEntity handleResignedUserException(
        ResignedUserException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ResponseDto(ResponseDto.FAIL, UNAUTHORIZED_MESSAGE));
    }

    @ExceptionHandler(NotFoundPromotionException.class)
    public ResponseEntity handleNotFoundPromotionException(
        NotFoundPromotionException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseDto(ResponseDto.FAIL, NOT_FOUND_PROMOTION_MESSAGE));
    }

    @ExceptionHandler(NotFoundProductException.class)
    public ResponseEntity handleNotFoundProductException(
        NotFoundProductException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseDto(ResponseDto.FAIL, NOT_FOUND_PRODUCT_MESSAGE));
    }
}
