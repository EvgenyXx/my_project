package com.example.my_project.controlelrs;

import com.example.my_project.dto.ProductDto;
import com.example.my_project.exception.NotFoundException;
import com.example.my_project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object>createProduct(@RequestBody @Valid ProductDto productDto){
        return ResponseEntity.ok(service.create(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto>updateProduct(@RequestBody @Valid ProductDto productDto,
                                        @PathVariable Long id){
        return ResponseEntity.ok(service.updateProduct(id, productDto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductDto>>findProductsByName(
            @PathVariable String name){
        List<ProductDto>productDto = service.findProductByName(name);
        if (productDto.isEmpty()){
            throw new NotFoundException("продукта с таким брендом нет");
        }
        return ResponseEntity.ok(productDto);
    }

}
