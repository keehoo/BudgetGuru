package com.kree.keehoo.budgetguru.Budget;


import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = ExpenseCategory.GET_ALL,
                query = "select u from ExpenseCategory u")
})
public class ExpenseCategory {

    public static final String GET_ALL = "ExpenseCategory.GET_ALL";

    @Id
            @GeneratedValue
    long id;
    String categoryName;

    public ExpenseCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public ExpenseCategory() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseCategory that = (ExpenseCategory) o;

        if (id != that.id) return false;
        return categoryName != null ? categoryName.equals(that.categoryName) : that.categoryName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}


