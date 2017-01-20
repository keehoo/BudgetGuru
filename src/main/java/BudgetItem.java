import javax.persistence.Embeddable;
import java.math.BigDecimal;
@Embeddable
class BudgetItem {

    private BigDecimal value;
    boolean isCost;

    BudgetItem(BigDecimal value) throws IllegalArgumentException {
        if (value.doubleValue()==0) throw new IllegalArgumentException("The value cannot be zero");
        this.value = value;
        isCost = value.doubleValue() < 0;
    }

    public BudgetItem() {
    }
}
