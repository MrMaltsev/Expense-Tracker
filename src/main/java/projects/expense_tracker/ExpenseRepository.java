package projects.expense_tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(s.amount) FROM Expense s WHERE s.user = :user")
    BigDecimal getTotalUserAmount(@Param("user") User user);

    List<Expense> findAllByUser(User user);

}
