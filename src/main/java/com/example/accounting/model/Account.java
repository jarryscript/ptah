package com.example.accounting.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {
    private String name;
    private BigDecimal balance;
}
