package com.ssg.promotion.product.application;

import com.ssg.promotion.Exception.product.NotFoundProductException;
import com.ssg.promotion.product.domain.ProductRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteProductService {

    private final ProductRepository productRepository;

    public void deleteProduct(long productId) {
        if(!productRepository.existsById(productId)){
            throw new NotFoundProductException();
        }
        productRepository.deleteById(productId);
    }
}
