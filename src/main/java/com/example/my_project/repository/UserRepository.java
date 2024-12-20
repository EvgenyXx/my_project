package com.example.my_project.repository;

import com.example.my_project.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

     boolean existsByNumberPhone(Long numberPhone);
     boolean existsByEmail(String email);
}
