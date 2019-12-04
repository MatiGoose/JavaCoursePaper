package Specifications.repair;

import Interfaces.Specification;
import Entities.Repair;

public class RepairIDSpecification implements Specification<Repair> {
    private int repairID;

    public RepairIDSpecification(int repairID) {
        this.repairID = repairID;
    }

    @Override
    public boolean specify(Repair repair) {
        return repairID==repair.getRepairID();
    }
}
