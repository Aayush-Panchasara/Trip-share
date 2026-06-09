package com.example.trip_share.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseRequest {

    
    private String title;

    private BigDecimal amount;

    private Long paidBy;

    private Long groupId;

    private List<Long> participantIds;
}
