package service;

import model.Customer;

import java.util.Collection;

public interface CustomerService {
    public static Long customerCount = 0L;
    public void addCustomer(String email, String firstName, String lastName);
    public void getCustomer(String email);
    public Collection<Customer> getAllCustomers();
}
