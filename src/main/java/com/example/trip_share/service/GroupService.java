package com.example.trip_share.service;

import org.springframework.stereotype.Service;

import com.example.trip_share.dto.GroupResponse;
import com.example.trip_share.entity.ExpenseGroup;
import com.example.trip_share.entity.GroupMember;
import com.example.trip_share.entity.User;
import com.example.trip_share.repository.GroupMemberRepository;
import com.example.trip_share.repository.GroupRepository;
import com.example.trip_share.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
     
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;

    public GroupResponse createGroup(String groupName, Long createdByUserId) {
        User creator = userRepository.findById(createdByUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        ExpenseGroup group = ExpenseGroup.builder()
                .groupName(groupName)
                .createdBy(creator)
                .build();
        
        ExpenseGroup savedGroup = groupRepository.save(group);
        
        return GroupResponse.builder()
                .id(savedGroup.getId())
                .groupName(savedGroup.getGroupName())
                .createdByUserId(creator.getId())
                .createdByUserName(creator.getName())
                .build();
    }

    public List<GroupResponse> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(group -> GroupResponse.builder()
                        .id(group.getId())
                        .groupName(group.getGroupName())
                        .createdByUserId(group.getCreatedBy().getId())
                        .createdByUserName(group.getCreatedBy().getName())
                        .build())
                .toList();
    }

    public GroupResponse getGroupById(Long groupId) {
        ExpenseGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .createdByUserId(group.getCreatedBy().getId())
                .createdByUserName(group.getCreatedBy().getName())
                .build();
    }

    @Transactional
    public void addMemberToGroup(Long groupId, Long userId) {
        ExpenseGroup group = groupRepository.findById(groupId).
                                orElseThrow(() -> new RuntimeException("Group not found")); 
                                
        User user = userRepository.findById(userId).
                        orElseThrow(() -> new RuntimeException("User not found")); 

        GroupMember member = GroupMember.builder()
                            .group(group)
                            .user(user)
                            .build();
        groupMemberRepository.save(member);
    }

}
