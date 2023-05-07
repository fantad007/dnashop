package com.dna.shop.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderCodes {
    @Getter
    @AllArgsConstructor
    public enum StatusFlag {
        STAGE_ONE(1, "đơn mới đặt"),
        STAGE_TWO(2, "đơn đang xử lý"),
        STAGE_THREE(3, "đơn đang được giao"),
        STAGE_FOUR(4, "đơn đã hoàn thành");

        private final Integer code;
        private final String display;

        public Integer getCode() {
            return this.code;
        }
    }
}
