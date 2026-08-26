package com.sai.expensetracker.controller;

import com.sai.expensetracker.model.Transaction;
import com.sai.expensetracker.model.User;
import com.sai.expensetracker.repository.TransactionRepository;
import com.sai.expensetracker.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository,
                                 UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactions(@PathVariable Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> addTransaction(@PathVariable Long userId,
                                            @RequestBody Transaction transaction) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (transaction.getType() == null ||
            (!transaction.getType().equalsIgnoreCase("INCOME") &&
             !transaction.getType().equalsIgnoreCase("EXPENSE"))) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Type must be INCOME or EXPENSE"));
        }

        if (transaction.getAmount() == null || transaction.getAmount() <= 0) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Amount must be greater than 0"));
        }

        transaction.setId(null);
        transaction.setUser(user);

        if (transaction.getDate() == null) {
            transaction.setDate(LocalDate.now());
        }

        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id,
                                               @RequestBody Transaction updated) {

        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setType(updated.getType());
                    transaction.setAmount(updated.getAmount());
                    transaction.setCategory(updated.getCategory());
                    transaction.setDescription(updated.getDescription());
                    transaction.setDate(updated.getDate());

                    return ResponseEntity.ok(
                            transactionRepository.save(transaction)
                    );
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {

        if (!transactionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        transactionRepository.deleteById(id);

        return ResponseEntity.ok(Map.of(
                "message", "Transaction deleted successfully"
        ));
    }
}
