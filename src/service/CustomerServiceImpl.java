package service;

import model.Customer;
import repository.CustomerRepository;
import repository.CustomerRepositoryImpl;

import java.util.Collection;
import java.util.logging.Logger;

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

    public void addCustomer(java.lang.String email, java.lang.String firstName, java.lang.String lastName) {
//        this.logger.info("Adding customer: " + email);
        this.customerRepository.addCustomer(email, firstName, lastName);
    }
    public Customer getCustomer(java.lang.String email) {
//        this.logger.info("Getting customer: " + email);
        return this.customerRepository.getACustomer(email);
    }

    public Collection<Customer> getAllCustomers() {
//        this.logger.info("Getting all customers");
        return this.customerRepository.getAllCustomers();
    }
}
