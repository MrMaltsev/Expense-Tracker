package projects.expense_tracker;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue Long id;
    private String description;
    private BigDecimal amount;

    private LocalDate date;

    private Category category;

    public enum Category {
        FOOD,
        CLOTHES,
        TRANSPORT,
        FUN,
        OTHER
    }

    Expense() {
        date = LocalDate.now();
    }

    Expense(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
        date = LocalDate.now();
    }
}
