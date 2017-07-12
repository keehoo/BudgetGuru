package com.kree.keehoo.budgetguru.Users;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedQueries({
        @NamedQuery(name = User.GET_ALL_USERS,
                query = "select u from User u"),
        @NamedQuery(name = User.GET_USER,
        query = "select u from User u where u.login = :login")
})
public class User {

    final public static String GET_ALL_USERS = "User.GET_ALL_USERS";
    final public static String GET_USER = "User.GET_USER";

    public User() {
    }

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<BudgetEntry> budgetEntries = new HashSet<>();

    public Set<BudgetEntry> getBudgetEntries() {
        return budgetEntries;
    }

    public void setBudgetEntries(Set<BudgetEntry> budgetEntries) {
        this.budgetEntries = budgetEntries;
    }

    public User(String login, String password, String name, String lastName, String email) {
        this.id = id;

        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
