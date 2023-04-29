package com.ssg.promotion.promotion.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.ssg.promotion.product.domain.Money;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    void calculateRatePriceTest() {
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now());
        Money money = new Money(10_000);
        Promotion promotion = Promotion.withOutId(period, PromotionType.RATE, 10,1L);
        assertEquals(new Money(9_000), promotion.calculatePrice(money));
    }

    @Test
    void calculatePromotionPriceTest() {
        Period period = new Period(LocalDateTime.now(), LocalDateTime.now());
        Money money = new Money(10_000);
        Promotion promotion = Promotion.withOutId(period, PromotionType.PRICE, 1_000,1L);
        assertEquals(new Money(9_000), promotion.calculatePrice(money));
    }
}