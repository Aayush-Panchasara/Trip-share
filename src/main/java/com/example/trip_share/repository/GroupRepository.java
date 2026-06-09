package com.example.trip_share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trip_share.entity.ExpenseGroup;

public interface GroupRepository extends JpaRepository<ExpenseGroup, Long> {

}
