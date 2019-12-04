package Specifications.purchase;

import Interfaces.Specification;
import Entities.Purchase;

public class PurchaseCarIDSpecification implements Specification<Purchase> {
    private int carID;

    public PurchaseCarIDSpecification(int carID) {
        this.carID = carID;
    }

    @Override
    public boolean specify(Purchase purchase) {
        return carID==purchase.getCarID();
    }
}
