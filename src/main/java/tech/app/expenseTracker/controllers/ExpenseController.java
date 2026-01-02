package tech.app.expenseTracker.controllers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.app.expenseTracker.entities.Expense;
import tech.app.expenseTracker.service.ExpenseService;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/expenses/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Expense saveExpense(@Valid @RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }

    @GetMapping("/expenses")
    public List<Expense> getExpenses(Pageable pageable){
        return expenseService.getAllExpenses(pageable).toList();
    }

    @GetMapping("/expenses/get")
    public Expense getExpenseById(@RequestParam("id") Long id){
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteExpense(@RequestParam("id") Long id){
        expenseService.deleteExpenseById(id);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable Long id,@RequestBody Expense expense){
        return expenseService.updateExpense(expense,id);
    }

    @GetMapping("/expenses/category")
    public List<Expense> readByCategory(@RequestParam("category") String category,Pageable pageable){
        return expenseService.readByCategory(category,pageable);
    }

    @GetMapping("/expenses/filter")
    public Set<Expense> filterByKeyword(@RequestParam("keyword") String keyword, Pageable pageable){
        return expenseService.findByKeyword(keyword, pageable);
    }

    @GetMapping("/expenses/filterByDateRange")
    public List<Expense> filterByDateRange(@RequestParam(value = "start",required = false)Date start,@RequestParam(value = "end",required = false) Date end,Pageable pageable){
        return expenseService.findByDateRange(start, end, pageable);
    }
}
