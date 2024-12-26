package com.example.my_project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
@Getter
@Setter
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private Long numberPhone;

    @Column(unique = true)
    private String email;

    private BigDecimal purse;

    private LocalDate birthday;

    private Integer age;

    private String passport;

    @OneToMany(mappedBy = "users")
    private List<Orders> orders;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

   
}
