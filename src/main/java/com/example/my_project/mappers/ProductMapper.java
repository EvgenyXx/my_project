package com.example.my_project.mappers;

import com.example.my_project.dto.ProductDto;
import com.example.my_project.entities.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "count",ignore = true)
    void updateProductFromDto(ProductDto productDto,@MappingTarget Product product);

    default Integer updateCount(ProductDto productDto,Product product){
        if ( productDto.getCount() == null ) {
            return product.getCount();
        }

        return product.getCount() + productDto.getCount();
    }
}
