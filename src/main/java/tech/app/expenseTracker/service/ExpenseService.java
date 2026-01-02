package tech.app.expenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.app.expenseTracker.entities.Expense;
import tech.app.expenseTracker.repository.ExpenseRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;


public interface ExpenseService {

    public Page<Expense> getAllExpenses(Pageable pageable);
    public Expense getExpenseById(Long id);
    public void deleteExpenseById(Long id);

    public Expense saveExpense(Expense expense);

    public Expense updateExpense(Expense expense,Long id);

    public List<Expense> readByCategory(String category,Pageable pageable);

    public Set<Expense> findByKeyword(String keyword, Pageable pageable);

    public List<Expense> findByDateRange(Date start,Date end,Pageable pageable);
}
