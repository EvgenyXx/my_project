package com.example.my_project.service;

import com.example.my_project.dto.ProductDto;
import com.example.my_project.entities.Product;
import com.example.my_project.exception.NotFoundException;
import com.example.my_project.exception.UniqueBrandException;
import com.example.my_project.mappers.ProductMapper;
import com.example.my_project.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //создание продукта
    public ProductDto create (ProductDto productDto){
        String error = validUniqueBrand(productDto);
        if (!error.isEmpty()){ // проверка сообщения на пустоту
            log.debug("ошибка уникальности {}",error);
            throw new UniqueBrandException(error);//если сообщение не пустое выбрасываю исключение
        }
        var product = ProductMapper.INSTANCE.productDtoToProduct(productDto);

        Product saveProduct = productRepository.save(product);
        log.info("товар сохранен {}",product.getBrand());
        return ProductMapper.INSTANCE.productToProductDto(saveProduct);
    }

    //обновление продукта
    public ProductDto updateProduct(Long id,ProductDto productDto){
        return productRepository.findById(id).map(product -> {
            // обновление всех остальных полей товара
            ProductMapper.INSTANCE.updateProductFromDto(productDto,product);
            //обновление кол-ва товара
            Integer count =   ProductMapper.INSTANCE.updateCount(productDto,product);
            product.setCount(count);
            log.info("количество товара {} увеличилось на {}",productDto.getBrand(),productDto.getCount());
            var saveProduct = productRepository.save(product);
            log.info("товар {} успешно обновлен",productDto.getBrand());
            return ProductMapper.INSTANCE.productToProductDto(saveProduct);
        }).orElseThrow(()-> new NotFoundException("продукт не найден"));

    }

    //получение всех продуктов одного бренда
    public List<ProductDto>findProductByName(String brand){
        return productRepository.findByBrand(brand).stream()
                .map(ProductMapper.INSTANCE::productToProductDto).toList();
    }

    // проверка уникальности бренда товара
    public String validUniqueBrand(ProductDto productDto){
        String message = "";
        if (productRepository.existsByBrand(productDto.getBrand())){
            message = "такой бренд товара уже есть";
        }
        return message;
    }

}
