import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by k on 20.02.17.
 */
@Stateless
public class BudgetEntryDao {

    @PersistenceContext
    private EntityManager entityManager;

    void saveDummyDataToDatabase() {
        BudgetEntry budgetEntry = new BudgetEntry();
        budgetEntry.setBudgetItem(new BudgetItem(new BigDecimal(100.0)));
        budgetEntry.setDateTime(LocalDateTime.now());
        try {
            if (entityManager == null) System.out.println("BudgetEntry is null");
        entityManager.persist(budgetEntry);
    }
    catch (NullPointerException nullPointer) {
        System.out.println("Null Pointer "+nullPointer.getMessage());
        System.out.println(nullPointer.toString());
    }
    }

}
