package com.example.my_project.service;

import com.example.my_project.dto.ProductDto;
import com.example.my_project.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Тест: проверка что бренд уникальный")
    void validUniqueBrand_validDate(){
        ProductDto productDto = new ProductDto();
        productDto.setBrand("sony");

        when(productRepository.existsByBrand(productDto.getBrand())).thenReturn(false);

        String messageFalse = productService.validUniqueBrand(productDto);
        assertEquals("",messageFalse);


    }

    @Test
    @DisplayName("Тест: проверка то что бренд уже существует")
    void validUniqueBrand_theBrandIsAlreadyInPlace(){
        ProductDto productDto = new ProductDto();
        productDto.setBrand("sony");
        when(productRepository.existsByBrand(productDto.getBrand())).thenReturn(true);
        String messageTru = productService.validUniqueBrand(productDto);
        assertEquals("такой бренд товара уже есть",messageTru);
    }


    @Test
    void findProductByName(){
        // Arrange
        String brand = "NonExistingBrand";
        when(productRepository.findByBrand(brand)).thenReturn(Collections.emptyList());


        // Act
        List<ProductDto> productDtos = productService.findProductByName(brand);


        // Assert
        assertTrue(productDtos.isEmpty());


    }



}