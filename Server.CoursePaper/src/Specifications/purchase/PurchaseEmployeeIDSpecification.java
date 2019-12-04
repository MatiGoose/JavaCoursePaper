package Specifications.purchase;

import Interfaces.Specification;
import Entities.Purchase;

public class PurchaseEmployeeIDSpecification implements Specification<Purchase> {
    private int employeeID;

    public PurchaseEmployeeIDSpecification(int employeeID) {
        this.employeeID=employeeID;
    }

    @Override
    public boolean specify(Purchase purchase) {
        return employeeID==purchase.getEmployeeID();
    }
}
