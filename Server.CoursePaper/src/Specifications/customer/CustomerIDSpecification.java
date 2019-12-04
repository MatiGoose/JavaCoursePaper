package Specifications.customer;

import Interfaces.Specification;
import Entities.Customer;

public class CustomerIDSpecification implements Specification<Customer> {
    private int customerID;

    public CustomerIDSpecification(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean specify(Customer customer) {
        return customerID==customer.getClientID();
    }
}
