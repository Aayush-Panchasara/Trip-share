package com.example.trip_share.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_share.dto.AddMemberRequest;
import com.example.trip_share.dto.CreateGroupRequest;
import com.example.trip_share.dto.GroupResponse;
import com.example.trip_share.service.GroupService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupResponse createGroup(@RequestBody CreateGroupRequest request) {
        return groupService.createGroup(request.getGroupName(), request.getCreatedByUserId());
    }

    @GetMapping
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{groupId}")
    public GroupResponse getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

    @PostMapping("/{groupId}/members")
    public String addMemberToGroup(@PathVariable Long groupId, @RequestBody AddMemberRequest request) {
        
        groupService.addMemberToGroup(groupId, request.getUserId());

        return "Member added successfully";
    }
}
