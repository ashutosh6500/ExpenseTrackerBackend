package tech.app.expenseTracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.app.expenseTracker.entities.Expense;
import java.util.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    //SELECT * FROM TABLE WHERE CATEGORY = category
    public Page<Expense> findByCategory(String category, Pageable pageable);

    //SELECT * FROM TABLE WHERE NAME LIKE '%keyword%'
    public Page<Expense> findByNameContaining(String keyword,Pageable pageable);

    //SELECT * FROM TABLE WHERE DESCRIPTION LIKE '%keyword%'
    public Page<Expense> findByDescriptionContaining(String keyword,Pageable pageable);

    //SELECT * FROM TABLE WHERE DATE BETWEEN 'startdate' and 'enddate'
    public Page<Expense> findByDateBetween(Date startDate,Date endDate,Pageable pageable);

    public Page<Expense> findByPersonId(Long userId,Pageable pageable);
}
