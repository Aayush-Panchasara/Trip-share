package com.example.trip_share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trip_share.entity.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

}
