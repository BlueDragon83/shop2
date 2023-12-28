package com.shop.domain.enums;

public enum OrderStatus {
    ORDER("ORDER", "판매")
    , CANCEL("CANCEL", "취소")
    , STOP("STOP", "중지");

    private final String code;
    private final String label;

    OrderStatus(String code, String label){
        this.code = code;
        this.label = label;
    }
}
