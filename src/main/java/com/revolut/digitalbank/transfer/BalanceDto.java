package com.revolut.digitalbank.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public class BalanceDto {

    @Getter
    private BigDecimal balance;

}
