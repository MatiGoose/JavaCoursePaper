package holder;

import Interfaces.Repository;
import Interfaces.Specification;
import Observer.PurchaseObserver;
import Entities.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHolder implements Repository<Purchase>
{
    private static PurchaseHolder instance;
    private ArrayList<Purchase> purchaseList;
    private PurchaseObserver observer = new PurchaseObserver();

    public static PurchaseHolder getInstance() {
        if (instance == null) {
            instance = new PurchaseHolder();
        }
        return instance;
    }

    private PurchaseHolder() {
        purchaseList = new ArrayList<>();
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    @Override
    public void add(Purchase purchase) {
        if (purchaseList.size() < 1) {
            purchase.setPurchaseID(1);
        } else {
            purchase.setPurchaseID(purchaseList.get(purchaseList.size() - 1).getPurchaseID() + 1);
        }
        purchaseList.add(purchase);
        observer.onAdd(purchase);
    }

    public void initialize(Purchase purchase) {
        purchaseList.add(purchase);
    }

    @Override
    public void remove(Purchase purchase) {
        purchaseList.remove(purchase);
        observer.onDelete(purchase);


    }

    @Override
    public void update(Purchase newPurchase, Purchase oldPurchase) {
        int purchasePos = purchaseList.indexOf(oldPurchase);
        newPurchase.setPurchaseID(oldPurchase.getPurchaseID());
        purchaseList.set(purchasePos, newPurchase);
        observer.onUpdate(newPurchase, oldPurchase);

    }

    @Override
    public List<Purchase> query(Specification<Purchase> specification) {
        List<Purchase> resultList = new ArrayList<>();
        for (Purchase purchase : purchaseList) {
            if (specification.specify(purchase)) {
                resultList.add(purchase);
            }
        }

        return resultList;
    }
}
