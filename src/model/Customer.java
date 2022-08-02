package model;

import util.RegexValidators;

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
}
