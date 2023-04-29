package com.ssg.promotion.product.ui.dto;

import com.ssg.promotion.product.domain.Product;
import com.ssg.promotion.product.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private long id;
    private String name;
    private String desc;
    private long money;
    private ProductType type;

    public static ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDesc(),
            product.getPrice().getValue(), product.getType());
    }
}
