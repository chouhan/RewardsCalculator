package com.kforce.rewards.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchData {

    private String property;
    private String value;
    private String operator;

    public SearchData(String property, String value, String operator) {
        super();
        this.property = property;
        this.value = value;
        this.operator = operator;
    }

}
