package repository;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final Collection<Customer> customer;
    private static CustomerRepositoryImpl instance = null;

    private CustomerRepositoryImpl() {
        this.customer = new HashSet<>();
    }

    public static CustomerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CustomerRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return this.customer;
    }

    @Override
    public Customer getACustomer(final String email) throws IllegalArgumentException {
        return this.customer.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    @Override
    public void addCustomer(final String email, final String firstName, final String lastName) throws IllegalArgumentException {
        if (customerExists(email)) {
            throw new IllegalArgumentException("Customer already exists.");
        }
        this.customer.add(new Customer(email, firstName, lastName));

    }

    @Override
    public void updateCustomer(final Customer customer) throws IllegalArgumentException {
        if (this.getACustomer(customer.getEmail()) == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        this.customer.remove(customer);
        this.customer.add(customer);
    }

    @Override
    public void deleteCustomer(final Customer customer) throws IllegalArgumentException {
        if (this.getACustomer(customer.getEmail()) == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        this.customer.remove(customer);
    }

    private boolean customerExists(final String email) {
        return this.customer.stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }
}
