package com.ssg.promotion.promotion.ui.dto;

import com.ssg.promotion.promotion.domain.PromotionType;
import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class RegisterPromotionRequest {

    @Min(1)
    private long productId;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDateTime;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDateTime;
    @NotNull
    private PromotionType type;
    @Min(1)
    private long value;
}
