package customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Implementation of the Customer Repository interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since   1.0
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> customer;
    private static CustomerRepositoryImpl instance = null;

    private CustomerRepositoryImpl() {
        this.customer = new HashMap<>();
    }

    public static synchronized CustomerRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (CustomerRepositoryImpl.class) {
                if (instance == null) {
                    instance = new CustomerRepositoryImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Returns all customers in the repository as a new set of Customer objects.
     * @return A new set of Customer objects.
     * @see customer.CustomerRepository#getAllCustomers()
     */
    @Override
    public Collection<Customer> getAllCustomers() {
        return new HashSet<>(this.customer.values());
    }

    /**
     * Returns the customer for the customer email.
     * @param email The email of the customer.
     * @return The customer for the customer email.
     * @throws IllegalArgumentException If the email does not exist in the repository.
     * @see customer.CustomerRepository#getCustomer(String)
     */
    @Override
    public Customer getCustomer(final String email) throws IllegalArgumentException {
        if (!this.customer.containsKey(email)) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.CUSTOMER_DOES_NOT_EXIST.getMessage(), email));
        }
        return this.customer.get(email);
    }

    /**
     * Creates a new customer in the repository.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param email The email of the customer.
     * @throws IllegalArgumentException If the email already exists in the repository.
     * @see customer.CustomerRepository#createCustomer(String, String, String)
     */
    @Override
    public void createCustomer(final String firstName, final String lastName, final String email) throws IllegalArgumentException {
        if (this.customerExists(email)) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.CUSTOMER_ALREADY_EXISTS.getMessage(), email));
        }
        this.customer.put(email, new Customer(firstName, lastName, email));

    }

    /**
     * Updates the customer in the repository.
     * @param customer The customer to update.
     * @throws IllegalArgumentException If the customer does not exist in the repository.
     * @see customer.CustomerRepository#updateCustomer(customer.Customer)
     */
    @Override
    public void updateCustomer(final Customer customer) throws IllegalArgumentException {
        if (!this.customerExists(customer.getEmail())) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.CUSTOMER_DOES_NOT_EXIST.getMessage(), customer.getEmail()));
        }
        this.customer.put(customer.getEmail(), customer);
    }

    /**
     * Deletes the customer from the repository.
     * @param customer The customer to delete.
     * @throws IllegalArgumentException If the customer does not exist in the repository.
     * @see customer.CustomerRepository#removeCustomer(Customer)
     */
    @Override
    public void removeCustomer(final Customer customer) throws IllegalArgumentException {
        if (!this.customerExists(customer.getEmail())) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.CUSTOMER_DOES_NOT_EXIST.getMessage(), customer.getEmail()));
        }
        this.customer.remove(customer.getEmail());
    }

    /**
     * Checks if the customer exists in the repository.
     * @param email The email of the customer.
     * @return True if the customer exists in the repository, false otherwise.
     */
    private boolean customerExists(final String email) {
        return this.customer.containsKey(email);
    }
}
