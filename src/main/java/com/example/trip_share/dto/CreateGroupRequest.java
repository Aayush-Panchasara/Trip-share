package com.example.trip_share.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequest {
    
    private String groupName;
    
    private Long createdByUserId;
}
