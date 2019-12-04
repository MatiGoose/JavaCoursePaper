package holder;

import Interfaces.Repository;
import Interfaces.Specification;
import Observer.ClientObserver;
import Entities.Customer;


import java.util.ArrayList;
import java.util.List;

public class ClientHolder implements Repository<Customer>
{
    private static ClientHolder instance;
    private ArrayList<Customer> customerList;
    private ClientObserver observer = new ClientObserver();


    public static ClientHolder getInstance() {
        if (instance == null) {
            instance = new ClientHolder();
        }
        return instance;
    }

    private ClientHolder() {
        customerList = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public void add(Customer customer) {
        if (customerList.size() < 0) {
            customer.setClientID(1);
        } else {
            customer.setClientID(customerList.get(customerList.size() - 1).getClientID() + 1);
        }
        customerList.add(customer);
        observer.onAdd(customer);
    }

    public void initialize(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerList.remove(customer);
        observer.onDelete(customer);

    }

    @Override
    public void update(Customer newCustomer, Customer oldCustomer) {
        int customerPos = customerList.indexOf(oldCustomer);
        newCustomer.setClientID(oldCustomer.getClientID());
        customerList.set(customerPos, newCustomer);
        observer.onUpdate(newCustomer, oldCustomer);


    }

    @Override
    public List<Customer> query(Specification<Customer> specification) {
        List<Customer> customers = new ArrayList<>();
        for (Customer customer : customerList) {
            if (specification.specify(customer)) {
                customers.add(customer);
            }
        }
        return customers;
    }
}
