package repository;

import model.Customer;

import java.util.Collection;

public interface CustomerRepository {
    public Collection<Customer> getAllCustomers() throws IllegalArgumentException;
    public Customer getACustomer(String email) throws IllegalArgumentException;
    public void addCustomer(String email, String firstName, String lastName);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
}
