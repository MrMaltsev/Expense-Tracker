package projects.expense_tracker;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseController {

    private final ExpenseService service;
    private final UserRepository userRepository;

    ExpenseController(ExpenseService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String homePage(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BigDecimal totalAmount = service.getTotalUserAmount(user);
        List<Expense> userExpenses = service.getAllUserExpenses(user);

        model.addAttribute("expenses", userExpenses);
        model.addAttribute("totalAmount", totalAmount);

        return "home";
    }

    @PostMapping("/home")
    public String returnToHome(@ModelAttribute Expense expense) {
        service.addExpense(expense);
        return "redirect:/home";
    }

    @GetMapping("/expense")
    public String addingExpense() {
        return "expense";
    }

    @PostMapping("/expense")
    public String addExpense(@ModelAttribute Expense expense) {
        service.addExpense(expense);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String showExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = service.getOneExpense(id);

        model.addAttribute("expense", expense);
        model.addAttribute("categories", Expense.Category.values());

        return "edit";
    }

    @PostMapping("/update/{id}")
        public String updateExpense(@PathVariable Long id, @ModelAttribute Expense newExpense) {
            service.updateExpense(id, newExpense);
            return "redirect:/home";
    }

}

