package Specifications.employee;

import Interfaces.Specification;
import Entities.Employee;

public class EmployeeSpecification implements Specification<Employee> {
    private int userID;
    public EmployeeSpecification(int userID){
        this.userID=userID;
    }

    @Override
    public boolean specify(Employee employee) {
        if(userID==employee.getEmployeeUserID()){
            return true;
        } else {
            return false;
        }
    }
}
