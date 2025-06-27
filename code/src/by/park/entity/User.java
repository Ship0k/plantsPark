package by.park.entity;

import java.util.Objects;

public class User {
    private String role;
    private String password;

    public User(String role, String password) {
        this.role = role;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(role, user.role) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "role='" + role + '\'' +
                ", password=" + password +
                '}';
    }
}
