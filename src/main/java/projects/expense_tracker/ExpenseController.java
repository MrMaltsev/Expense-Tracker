package projects.expense_tracker;

import java.util.List;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExpenseController {

    private final ExpenseService service;
    ExpenseController(ExpenseService service) {this.service = service;}

    @GetMapping("/home")
    public String homePage(Model model) {
        List<Expense> expenses = service.getAllExpenses();

        model.addAttribute("expenses", expenses);

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
}
