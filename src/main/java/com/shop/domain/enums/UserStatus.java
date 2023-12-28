package com.shop.domain.enums;

public enum UserStatus {
    NORMAL("NORMAL", "정상")
    , REST("REST", "휴먼")
    , STOP("STOP", "중지")
    , DROP("DROP", "탈퇴");

    private final String code;
    private final String label;

    UserStatus(String code, String label){
        this.code = code;
        this.label = label;

    }
}
