package Specifications.repair;

import Interfaces.Specification;
import Entities.Repair;

public class RepairEmployeeIDSpecification implements Specification<Repair> {
    private int employeeID;
    public RepairEmployeeIDSpecification(int employeeID){
        this.employeeID=employeeID;
    }

    @Override
    public boolean specify(Repair repair) {
        return employeeID==repair.getEmployeeID();

    }
}
