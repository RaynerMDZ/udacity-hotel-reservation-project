package service;

import model.Customer;

import java.util.Collection;

public interface CustomerService {
    public void addCustomer(String email, String firstName, String lastName);
    public Customer getCustomer(String email);
    public Collection<Customer> getAllCustomers();
}
