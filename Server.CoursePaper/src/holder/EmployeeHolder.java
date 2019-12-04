package holder;

import Interfaces.Repository;
import Interfaces.Specification;
import Observer.EmployeeObserver;
import Entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHolder implements Repository<Employee>
{
    private static EmployeeHolder instance;
    private ArrayList<Employee> employeeList;
    private EmployeeObserver observer = new EmployeeObserver();

    public static EmployeeHolder getInstance() {
        if (instance == null) {
            instance = new EmployeeHolder();
        }
        return instance;
    }

    private EmployeeHolder() {
        employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    @Override
    public void add(Employee employee) {
        if (employeeList.size() < 1) {
            employee.setEmployeeID(1);
        } else {
            employee.setEmployeeID(employeeList.get(employeeList.size() - 1).getEmployeeID() + 1);
        }
        employeeList.add(employee);
        observer.onAdd(employee);
    }

    public void initialize(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        employeeList.remove(employee);
        observer.onDelete(employee);

    }

    @Override
    public void update(Employee newEmployee, Employee oldEmployee) {
        int employeePos = employeeList.indexOf(oldEmployee);
        newEmployee.setEmployeeID(oldEmployee.getEmployeeID());
        employeeList.set(employeePos, newEmployee);
        observer.onUpdate(newEmployee, oldEmployee);

    }

    @Override
    public List<Employee> query(Specification<Employee> specification) {
        List<Employee> resultList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (specification.specify(employee)) {
                resultList.add(employee);
            }
        }
        return resultList;
    }
}
