package Specifications.purchase;

import Interfaces.Specification;
import Entities.Purchase;

public class PurchaseClientIDSpecification implements Specification<Purchase> {
    private int clientID;

    public PurchaseClientIDSpecification(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public boolean specify(Purchase purchase) {
        return clientID==purchase.getClientID();
    }
}
