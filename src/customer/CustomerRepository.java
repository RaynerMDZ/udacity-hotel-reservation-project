package customer;

import java.util.Collection;

/**
 * Customer Repository interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public interface CustomerRepository {
    public Collection<Customer> getAllCustomers();
    public Customer getCustomer(String email) throws IllegalArgumentException;
    public void createCustomer(String email, String firstName, String lastName) throws IllegalArgumentException;
    public void updateCustomer(Customer customer) throws IllegalArgumentException;
    public void removeCustomer(Customer customer) throws IllegalArgumentException;
}
