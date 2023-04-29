package com.ssg.promotion.product.ui.dto;

import com.ssg.promotion.promotion.domain.Period;
import com.ssg.promotion.promotion.domain.Promotion;
import com.ssg.promotion.promotion.domain.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromotionDto {

    private long id;
    private Period period;
    private PromotionType type;
    private long value;


    public static PromotionDto mapPromotionToPromotionDto(Promotion promotion) {
        return new PromotionDto(promotion.getId(), promotion.getPeriod(), promotion.getType(),
            promotion.getValue());
    }
}
