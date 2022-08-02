package service;

import model.Customer;

import java.util.Collection;

public interface CustomerService {
    public void addCustomer(java.lang.String email, java.lang.String firstName, java.lang.String lastName);
    public Customer getCustomer(java.lang.String email);
    public Collection<Customer> getAllCustomers();
}
