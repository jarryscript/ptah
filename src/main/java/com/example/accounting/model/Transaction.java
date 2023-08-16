package com.example.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Account account;
    private TransactionDirection direction;
    private BigDecimal amount;
    private LocalTime time;
}
