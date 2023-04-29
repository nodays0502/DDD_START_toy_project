package com.ssg.promotion.product.application;

import com.ssg.promotion.product.ui.dto.ProductDto;
import com.ssg.promotion.product.domain.Product;
import com.ssg.promotion.product.domain.ProductType;
import com.ssg.promotion.product.domain.ProductRepository;
import com.ssg.promotion.user.domain.User;
import com.ssg.promotion.user.domain.UserState;
import com.ssg.promotion.user.domain.UserType;
import com.ssg.promotion.Exception.user.NotFoundUserException;
import com.ssg.promotion.Exception.user.ResignedUserException;
import com.ssg.promotion.user.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GetProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ProductDto> getProduct(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new NotFoundUserException();
        });
        if (user.getState() == UserState.RESIGN) {
            throw new ResignedUserException();
        }
        List<ProductType> types = new ArrayList<>();
        types.add(ProductType.NORMAL);
        if (user.getType() == UserType.BUSINESS) {
            types.add(ProductType.BUSINESS);
        }
        List<Product> products = productRepository.findProductsByType(types);
        return products.stream().map(ProductDto::mapProductToProductDto)
            .collect(Collectors.toList());
    }
}
