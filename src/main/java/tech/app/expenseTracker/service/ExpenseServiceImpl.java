package tech.app.expenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.app.expenseTracker.entities.Expense;
import tech.app.expenseTracker.entities.Person;
import tech.app.expenseTracker.exceptions.ResourceNotFoundException;
import tech.app.expenseTracker.repository.ExpenseRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private PersonService personService;
    @Override
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findByPersonId(personService.getLoggedInUser().getId(),pageable);
    }

    @Override
    public Expense getExpenseById(Long id){
        Person currentUser = personService.getLoggedInUser();
        for(Expense exp : expenseRepository.findAll()){
            if(exp.getPerson().getUsername().equals(currentUser.getUsername()))
            {
                if(exp.getId() == id)
                    return exp;
            }
        }

        throw new ResourceNotFoundException("Expense Not Found For Id "+id);
    }
    @Override
    public void deleteExpenseById(Long id){
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);

    }

    @Override
    public Expense saveExpense(Expense expense){
        expense.setPerson(personService.getLoggedInUser()); //mapping user to expense as every expense has user
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense,Long id){
        Expense exp = expenseRepository.findById(id).get();
        exp.setUpdated_at((expense.getUpdated_at() != null) ? (expense.getUpdated_at()) : exp.getUpdated_at() );
        exp.setAmount(expense.getAmount() != null ? expense.getAmount() : exp.getAmount());
        exp.setCategory(expense.getCategory() != null ? expense.getCategory() : exp.getCategory());
        exp.setDate(expense.getDate() != null ? expense.getDate() : exp.getDate());
        exp.setDescription(expense.getDescription() != null ? expense.getDescription() : exp.getDescription());
        exp.setName(expense.getName() != null ? expense.getName() : exp.getName());
        return exp;
    }

    @Override
    public List<Expense> readByCategory(String category,Pageable pageable){
        return expenseRepository.findByCategory(category,pageable).toList();
    }
    //pending!
    @Override
    public Set<Expense> findByKeyword(String keyword, Pageable pageable){
        Set<Expense> matchingExpenses = expenseRepository.findByNameContaining(keyword, pageable).toSet();
        for(var expense : expenseRepository.findByDescriptionContaining(keyword, pageable).toList())
            matchingExpenses.add(expense);
        return matchingExpenses;
    }

    @Override
    public List<Expense> findByDateRange(Date start,Date end,Pageable pageable){
        if(start == null)
            start = new Date(0);
        if(end == null)
            end = new Date(System.currentTimeMillis());
        return expenseRepository.findByDateBetween(start, end, pageable).toList();
    }
}
