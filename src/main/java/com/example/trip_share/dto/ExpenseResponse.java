package com.example.trip_share.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExpenseResponse {
    
    private Long id;
    
    private String title;
    
    private BigDecimal amount;
    
    private Long paidByUserId;
    
    private String paidByUserName;
    
    private Long groupId;
    
    private String groupName;
}
