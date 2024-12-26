package com.example.my_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private Long id;

    private String brand;

    private Integer price;

    private Integer count;
}
