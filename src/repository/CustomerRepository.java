package repository;

import model.Customer;

import java.util.Collection;

public interface CustomerRepository {
    public Collection<Customer> getAllCustomers();
    public Customer getACustomer(java.lang.String email) throws IllegalArgumentException;
    public void addCustomer(java.lang.String email, java.lang.String firstName, java.lang.String lastName) throws IllegalArgumentException;
    public void updateCustomer(Customer customer) throws IllegalArgumentException;
    public void deleteCustomer(Customer customer) throws IllegalArgumentException;
}
