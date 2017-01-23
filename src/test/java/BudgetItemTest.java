import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BudgetItemTest {
    @Test
    public void getValueTestShouldResturnMinus500() throws Exception {
        BudgetItem budgetItem = new BudgetItem(new BigDecimal(-500.00));
        Double actual = budgetItem.getValue().doubleValue();
        assertEquals(-500, actual, .5);
    }


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