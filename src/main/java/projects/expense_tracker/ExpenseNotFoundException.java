package projects.expense_tracker;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Long id) {
        super("Could not find expense" + id);
    }
}
