package com.revolut.digitalbank.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RequestDto {

    @Getter
    private Integer from;

    @Getter
    private Integer to;

    @Getter
    private String value;

}
