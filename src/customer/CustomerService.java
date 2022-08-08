package customer;

import java.util.Collection;

public interface CustomerService {
    public Collection<Customer> getAllCustomers();
    public Customer getCustomer(String email);
    public void createCustomer(String email, String firstName, String lastName);
    public void updateCustomer(Customer customer) throws IllegalArgumentException;
    public void deleteCustomer(Customer customer) throws IllegalArgumentException;
}
