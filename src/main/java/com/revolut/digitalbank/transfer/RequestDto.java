package com.revolut.digitalbank.transfer;

public class RequestDto {

    private Integer from;

    private Integer to;

    private String value;

    public RequestDto(Integer from, Integer to, String value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }
}
