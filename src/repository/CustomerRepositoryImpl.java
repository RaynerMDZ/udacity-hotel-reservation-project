package repository;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static CustomerRepositoryImpl instance = null;

    private CustomerRepositoryImpl() {
    }

    public static CustomerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CustomerRepositoryImpl();
        }
        return instance;
    }

    private final Collection<Customer> customer = new HashSet<>();

    @Override
    public Collection<Customer> getAllCustomers() {
        return this.customer;
    }

    @Override
    public Customer getACustomer(String email) throws IllegalArgumentException {
        return this.customer.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    @Override
    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        if (this.getACustomer(email) == null) {
            this.customer.add(new Customer(email, firstName, lastName));
            return;
        }
        throw new IllegalArgumentException("Customer already exists");
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (this.getACustomer(customer.getEmail()) == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        this.customer.remove(customer);
        this.customer.add(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        if (this.getACustomer(customer.getEmail()) == null) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        this.customer.remove(customer);
    }
}
