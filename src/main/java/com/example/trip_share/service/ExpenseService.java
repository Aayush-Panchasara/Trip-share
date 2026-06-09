package com.example.trip_share.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.trip_share.dto.CreateExpenseRequest;
import com.example.trip_share.dto.ExpenseResponse;
import com.example.trip_share.entity.Expense;
import com.example.trip_share.entity.ExpenseGroup;
import com.example.trip_share.entity.ExpenseShare;
import com.example.trip_share.entity.User;
import com.example.trip_share.repository.ExpenseRepository;
import com.example.trip_share.repository.ExpeseShareRepository;
import com.example.trip_share.repository.GroupRepository;
import com.example.trip_share.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final UserRepository userrepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpeseShareRepository expeseShareRepository;

    @Transactional
    public ExpenseResponse createExpense(CreateExpenseRequest request) {
        
        User paidByUser = userrepository.findById(request.getPaidBy()).orElseThrow(() -> new RuntimeException("User not found"));

        ExpenseGroup group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));

        Expense expense = Expense.builder()
            .title(request.getTitle())
            .amount(request.getAmount())
            .paidBy(paidByUser)
            .group(group)
            .build();

        expenseRepository.save(expense);

        BigDecimal shareAmount = request.getAmount().divide(BigDecimal.valueOf(request.getParticipantIds().size()+1));

        ExpenseShare paid = ExpenseShare.builder()
                .expense(expense)
                .user(paidByUser)
                .shareAmount(shareAmount)
                .build();
            expeseShareRepository.save(paid);
        
        for (Long participantId : request.getParticipantIds()) {
            
            User user = userrepository.findById(participantId).orElseThrow(() -> new RuntimeException("User not found"));


            ExpenseShare share = ExpenseShare.builder()
                .expense(expense)
                .user(user)
                .shareAmount(shareAmount)
                .build();
            expeseShareRepository.save(share);
        }

        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .paidByUserId(paidByUser.getId())
                .paidByUserName(paidByUser.getName())
                .groupId(group.getId())
                .groupName(group.getGroupName())
                .build();
    }

    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expense -> ExpenseResponse.builder()
                        .id(expense.getId())
                        .title(expense.getTitle())
                        .amount(expense.getAmount())
                        .paidByUserId(expense.getPaidBy().getId())
                        .paidByUserName(expense.getPaidBy().getName())
                        .groupId(expense.getGroup().getId())
                        .groupName(expense.getGroup().getGroupName())
                        .build())
                .toList();
    }

    public ExpenseResponse getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        
        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .paidByUserId(expense.getPaidBy().getId())
                .paidByUserName(expense.getPaidBy().getName())
                .groupId(expense.getGroup().getId())
                .groupName(expense.getGroup().getGroupName())
                .build();
    }
}
