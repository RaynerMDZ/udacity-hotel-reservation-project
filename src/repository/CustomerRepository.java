package repository;

import model.Customer;

import java.util.Collection;

public interface CustomerRepository {
    public Collection<Customer> getAllCustomers();
    public Customer getACustomer(String email) throws IllegalArgumentException;
    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException;
    public void updateCustomer(Customer customer) throws IllegalArgumentException;
    public void deleteCustomer(Customer customer) throws IllegalArgumentException;
}
