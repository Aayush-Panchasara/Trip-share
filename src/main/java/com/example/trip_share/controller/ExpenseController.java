package com.example.trip_share.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_share.dto.CreateExpenseRequest;
import com.example.trip_share.dto.ExpenseResponse;
import com.example.trip_share.service.ExpenseService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseResponse createExpense(@RequestBody CreateExpenseRequest request) {
        return expenseService.createExpense(request);
    }

    @GetMapping
    public List<ExpenseResponse> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable Long expenseId) {
        return expenseService.getExpenseById(expenseId);
    }
}
