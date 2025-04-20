package projects.expense_tracker;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Expense addExpense(Expense expense) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException(("User not found")));

        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllUserExpenses(User user) {
        return expenseRepository.findAllByUser(user);
    }

    public Expense getOneExpense(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    public BigDecimal getTotalUserAmount(User user) {
        return expenseRepository.getTotalUserAmount(user);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense expense = expenseRepository.findById(id).
                orElseThrow(() -> new ExpenseNotFoundException(id));

        expense.setDate(LocalDate.now());
        expense.setDescription(updatedExpense.getDescription());
        expense.setAmount(updatedExpense.getAmount());
        expense.setCategory(updatedExpense.getCategory());

        return expenseRepository.save(expense);
    }
    
}
