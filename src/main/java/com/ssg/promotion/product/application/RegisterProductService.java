package com.ssg.promotion.product.application;

import com.ssg.promotion.product.ui.dto.RegisterProductRequest;
import com.ssg.promotion.product.domain.Product;
import com.ssg.promotion.product.domain.ProductRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterProductService {

    private final ProductRepository productRepository;

    public void registerProduct(RegisterProductRequest registerProductRequestDto) {
        Product product = RegisterProductRequest.mapToProduct(registerProductRequestDto);
        productRepository.save(product);
    }
}
