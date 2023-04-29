package com.ssg.promotion.product.application;

import com.ssg.promotion.product.domain.CalculateLowestPromotionPriceService;
import com.ssg.promotion.product.domain.ProductRepository;
import com.ssg.promotion.product.ui.dto.GetPromotionInfoResponse;
import com.ssg.promotion.product.domain.Money;
import com.ssg.promotion.product.domain.Product;
import com.ssg.promotion.Exception.product.NotFoundProductException;
import com.ssg.promotion.promotion.domain.Promotion;
import com.ssg.promotion.promotion.domain.PromotionRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GetPromotionInfoService {

    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;
    private final CalculateLowestPromotionPriceService calculateLowestPromotionPriceService;

    public GetPromotionInfoResponse findPromotionLowestPrice(long productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> {
            throw new NotFoundProductException();
        });

        List<Promotion> promotions = promotionRepository.findPromotionByProductIdAndPeriod(
            productId,
            LocalDateTime.now());

        Money lowestPrice = product.findLowestPromotionPrice(calculateLowestPromotionPriceService,
            promotions);

        return GetPromotionInfoResponse.mapProductToGetPromotionInfoResponseDto(product,
            promotions, lowestPrice);
    }
}
