package Specifications.purchase;

import Interfaces.Specification;
import Entities.Purchase;

public class PurchaseIDSpecification implements Specification<Purchase>
{
    private int purchaseID;

    public PurchaseIDSpecification(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    @Override
    public boolean specify(Purchase purchase) {
        return purchaseID==purchase.getPurchaseID();
    }
}
