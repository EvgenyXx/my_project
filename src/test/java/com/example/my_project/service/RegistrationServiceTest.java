package com.example.my_project.service;

import com.example.my_project.dto.UserRegistrationDto;
import com.example.my_project.entities.Users;
import com.example.my_project.exception.RegistrationException;
import com.example.my_project.mappers.UserRegistrationMapper;
import com.example.my_project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RegistrationService registrationService;




}