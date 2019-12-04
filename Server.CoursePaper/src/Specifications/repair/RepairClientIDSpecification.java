package Specifications.repair;

import Interfaces.Specification;
import Entities.Repair;

public class RepairClientIDSpecification implements Specification<Repair> {
    private int clientID;

    public RepairClientIDSpecification(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public boolean specify(Repair repair) {
        return clientID==repair.getClientID();
    }
}
