package com.shop.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum ItemSellStatus {
    SELL("SELL", "판매")
    , SOLD_OUT("SOLD_OUT", "매진");

    private final String code;
    private final String label;
}
