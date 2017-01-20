import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class BudgetItemTest {


    @Test
    public void shouldBeACostForNegativeBudgetItemValue() {
        BudgetItem budgetItem = new BudgetItem(new BigDecimal(-500.00));
        assertTrue(budgetItem.isCost);
    }

    @Test
    public void shouldNotBeACost() {
        BudgetItem budgetItem = new BudgetItem(new BigDecimal(1000.00));
        assertTrue(!budgetItem.isCost);
    }

    @Test (expected=IllegalArgumentException.class)
    public void shouldReturnAnExceptionForZeroValue() {
        BudgetItem budgetItem = new BudgetItem(new BigDecimal(0));
    }
}