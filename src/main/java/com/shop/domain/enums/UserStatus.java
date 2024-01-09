package com.shop.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    NORMAL("NORMAL", "정상")
    , REST("REST", "휴먼")
    , STOP("STOP", "중지")
    , DROP("DROP", "탈퇴");

    private final String code;
    private final String label;
}
