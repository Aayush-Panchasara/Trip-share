package com.example.trip_share.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GroupResponse {
    
    private Long id;
    
    private String groupName;
    
    private Long createdByUserId;
    
    private String createdByUserName;
}
