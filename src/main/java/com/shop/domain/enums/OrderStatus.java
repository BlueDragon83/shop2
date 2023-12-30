package com.shop.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDER("ORDER", "판매")
    , CANCEL("CANCEL", "취소")
    , STOP("STOP", "중지");

    private final String code;
    private final String label;
}
