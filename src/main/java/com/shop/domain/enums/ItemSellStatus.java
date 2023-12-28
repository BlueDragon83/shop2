package com.shop.domain.enums;

public enum ItemSellStatus {
    SELL("SELL", "판매")
    , SOLD_OUT("SOLD_OUT", "매진");

    private final String code;
    private final String label;

    ItemSellStatus(String code, String label){
        this.code = code;
        this.label = label;
    }
}
