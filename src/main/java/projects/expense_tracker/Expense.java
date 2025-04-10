package projects.expense_tracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private Category category;

    public enum Category {
        FOOD,
        CLOTHES,
        TRANSPORT,
        FUN,
        OTHER
    }

    Expense() {}

    Expense(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
        date = LocalDateTime.now();
    }
}
