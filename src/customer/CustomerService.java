package customer;

import java.util.Collection;

/**
 * Customer Service interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public interface CustomerService {
    public Collection<Customer> getAllCustomers();
    public Customer getCustomer(String email);
    public void createCustomer(String firstName, String lastName, String email);
    public void updateCustomer(Customer customer) throws IllegalArgumentException;
    public void deleteCustomer(Customer customer) throws IllegalArgumentException;
}
