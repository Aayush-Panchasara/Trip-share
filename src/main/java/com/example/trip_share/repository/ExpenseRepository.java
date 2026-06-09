package com.example.trip_share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trip_share.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
