package com.example.my_project.controlelrs;

import com.example.my_project.dto.UserAccountDto;
import com.example.my_project.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>updateUsers (
            @PathVariable Long id, @RequestBody @Valid UserAccountDto userAccountDto){
        return ResponseEntity.ok(userAccountService.updateUser(userAccountDto, id));
    }
}
