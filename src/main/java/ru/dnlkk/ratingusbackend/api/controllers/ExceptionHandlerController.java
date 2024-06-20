package ru.dnlkk.ratingusbackend.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.dnlkk.ratingusbackend.exceptions.ForbiddenException;
import ru.dnlkk.ratingusbackend.exceptions.LogicException;
import ru.dnlkk.ratingusbackend.exceptions.NotFoundException;

@RestController
public class ExceptionHandlerController {
    @ExceptionHandler({NotFoundException.class, ForbiddenException.class, LogicException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
