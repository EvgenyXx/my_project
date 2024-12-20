package com.example.my_project.controlelrs;

import com.example.my_project.dto.UserRegistrationDto;
import com.example.my_project.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

   private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<UserRegistrationDto>create
            (@RequestBody @Valid UserRegistrationDto users){
        return ResponseEntity.ok(registrationService.create(users));
    }
}
