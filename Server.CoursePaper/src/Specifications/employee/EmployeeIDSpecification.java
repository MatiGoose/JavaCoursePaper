package Specifications.employee;

import Interfaces.Specification;
import Entities.Employee;

public class EmployeeIDSpecification implements Specification<Employee> {
    private int employeeID;
    public EmployeeIDSpecification(int employeeID){
        this.employeeID=employeeID;
    }

    @Override
    public boolean specify(Employee employee) {
        return employeeID==employee.getEmployeeID();
    }
}
