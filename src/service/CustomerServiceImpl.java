package service;

import model.Customer;
import repository.CustomerRepository;
import repository.CustomerRepositoryImpl;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
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

    public void addCustomer(String email, String firstName, String lastName) {
        try {
            this.customerRepository.addCustomer(email, firstName, lastName);
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
        }
    }
    public Customer getCustomer(String email) {
        try {
            return this.customerRepository.getACustomer(email);
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return this.customerRepository.getAllCustomers();
    }
}
