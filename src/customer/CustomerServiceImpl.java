package customer;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Implementation of the CustomerService interface.
 *
 * @author Rayner Mendez
 * @version 1.0
 * @since 1.0
 */
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());
    private final CustomerRepository customerRepository;
    private static CustomerServiceImpl instance = null;

    private CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl(CustomerRepositoryImpl.getInstance());
        }
        return instance;
    }

    /**
     * Return a collection of all customers.
     * @return Collection of customers.
     * @see CustomerRepository#getAllCustomers()
     */
    public Collection<Customer> getAllCustomers() {
        return this.customerRepository.getAllCustomers();
    }

    /**
     * Returns the customer with the given email.
     * @param email The email of the customer.
     * @return The customer with the given email.
     * @throws IllegalArgumentException If the email is null or empty.
     * @see CustomerRepository#getCustomer(String)
     */
    public Customer getCustomer(final String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.EMAIL_CANNOT_BE_NULL_OR_EMPTY.getMessage(), email));
        }
        return this.customerRepository.getCustomer(email);
    }

    /**
     * Creates a new customer.
     * @param email The email of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @throws IllegalArgumentException If the email is null or empty.
     * @throws IllegalArgumentException If the first name is null or empty.
     * @throws IllegalArgumentException If the last name is null or empty.
     * @see CustomerRepository#createCustomer(String, String, String)
     */
    public void createCustomer(final String email, final String firstName, final String lastName) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.EMAIL_CANNOT_BE_NULL_OR_EMPTY.getMessage(), email));
        }

        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY.getMessage(), firstName));
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException(String.format(CustomerErrorMessages.LAST_NAME_CANNOT_BE_NULL_OR_EMPTY.getMessage(), lastName));
        }
        this.customerRepository.createCustomer(email, firstName, lastName);
    }

    /**
     * Updates a customer with a new object.
     * @param customer The customer object to update.
     * @throws IllegalArgumentException If the customer object is null.
     * @see CustomerRepository#updateCustomer(Customer)
     */
    @Override
    public void updateCustomer(Customer customer) throws IllegalArgumentException {
        if (customer == null) {
            throw new IllegalArgumentException(CustomerErrorMessages.CUSTOMER_CANNOT_BE_NULL.getMessage());
        }

        this.customerRepository.updateCustomer(customer);
    }

    /**
     * Deletes a customer.
     * @param customer The customer object to delete.
     * @throws IllegalArgumentException If the email is null or empty.
     * @see CustomerRepository#removeCustomer(Customer)
     */
    @Override
    public void deleteCustomer(Customer customer) throws IllegalArgumentException {
        if (customer == null) {
            throw new IllegalArgumentException(CustomerErrorMessages.CUSTOMER_CANNOT_BE_NULL.getMessage());
        }
        this.customerRepository.removeCustomer(customer);
    }
}
