package com.ssg.promotion.promotion.application;

import static org.junit.jupiter.api.Assertions.*;

import com.ssg.promotion.product.domain.Money;
import com.ssg.promotion.product.domain.Product;
import com.ssg.promotion.product.domain.ProductType;
import com.ssg.promotion.promotion.domain.Period;
import com.ssg.promotion.promotion.domain.Promotion;
import com.ssg.promotion.promotion.domain.PromotionType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.aspectj.weaver.patterns.PerObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DeletePromotionServiceTest {

    @Autowired
    private DeletePromotionService deletePromotionService;

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void deletePromotionTest() {
        Product product = Product.withOutId("마우스", "버티컬 마우스", new Money(17_000L),
            ProductType.NORMAL);
        em.persist(product);
        long productId = em.createQuery("select p.id from Product p where p.name = '마우스'",
            Long.class).getSingleResult();
        Promotion promotion = Promotion.withOutId(
            new Period(LocalDateTime.now(), LocalDateTime.now()),
            PromotionType.PRICE, 10_000, productId);
        System.out.println(productId);
        System.out.println(promotion.getProductId());
        em.persist(promotion);
        long promotionId = em.createQuery(
                "select p.id from Promotion p where p.productId = :productId", Long.class)
            .setParameter("productId", productId).getSingleResult();
        deletePromotionService.deletePromotion(promotionId);
        assertNull(em.find(Promotion.class,promotionId));
    }
}