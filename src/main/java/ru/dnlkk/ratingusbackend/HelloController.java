package ru.dnlkk.ratingusbackend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public ResponseEntity<Dto> get() {
        return ResponseEntity.ok(new Dto("хау ар ю? айм файн фенкью!"));
    }
}
