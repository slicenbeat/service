package com.vague.service.service;

import lombok.Data;

@Data
public class ClassForGetStat {
    private String percent;
    private String value;

    public ClassForGetStat() {
    }

    public ClassForGetStat(String percent, String value) {
        this.percent = percent;
        this.value = value;
    }
}
