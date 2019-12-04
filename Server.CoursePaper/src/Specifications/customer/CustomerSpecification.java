package Specifications.customer;

import Interfaces.Specification;
import Entities.Customer;

public class CustomerSpecification implements Specification<Customer> {
    private String passportNumber;
    public CustomerSpecification(String passportNumber){
        this.passportNumber=passportNumber;
    }

    @Override
    public boolean specify(Customer customer) {
        return (passportNumber.equals(customer.getPassportNumber()));
    }
}
