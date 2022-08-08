package customer;

import util.RegexValidators;

import java.util.Objects;

public class Customer {
    private java.lang.String firstName;
    private java.lang.String lastName;
    private java.lang.String email;

    public Customer(java.lang.String firstName, java.lang.String lastName, java.lang.String email) {
        if (RegexValidators.validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address!");
        }
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public java.lang.String getFirstName() {
        return firstName;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public java.lang.String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 97 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 97 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
}
