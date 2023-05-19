package com.dnahealth.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AdminCodes {
    @Getter
    @AllArgsConstructor
    public enum DeleteFlag {
        NORMAL(0, "normal"),
        DELETED(1, "deleted");

        private final Integer code;
        private final String display;

        public Integer getCode() {
            return this.code;
        }
    }
}
