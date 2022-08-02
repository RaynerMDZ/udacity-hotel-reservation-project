package model;

import util.RegexValidators;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        if (RegexValidators.validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address!");
        }
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email;
    }
}
