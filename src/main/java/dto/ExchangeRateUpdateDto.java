package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRateUpdateDto {
    @NotNull
    private String baseCurrencyCode;
    @NotNull
    private String targetCurrencyCode;
    @NotNull
    private BigDecimal rate;
}
