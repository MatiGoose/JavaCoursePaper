package Server;

import Specifications.car.CarSpecification;
import Specifications.customer.CustomerIDSpecification;
import Specifications.customer.CustomerSpecification;
import Specifications.employee.EmployeeIDSpecification;
import Specifications.employee.EmployeeSpecification;
import Specifications.purchase.PurchaseCarIDSpecification;
import Specifications.purchase.PurchaseClientIDSpecification;
import Specifications.purchase.PurchaseEmployeeIDSpecification;
import Specifications.purchase.PurchaseIDSpecification;
import Specifications.repair.RepairClientIDSpecification;
import Specifications.repair.RepairEmployeeIDSpecification;
import Specifications.repair.RepairIDSpecification;
import Specifications.user.UserIDSpecification;
import Specifications.user.UserSpecification;
import Entities.*;
import holder.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CommandExecuter {
    private ObjectOutputStream outputStream;

    public CommandExecuter(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;

    }

    public void executeCommand(String command) {
        String[] message = command.split("/");
        try {
            switch (message[0]) {
                case "getCarList": {
                    List<Car> cars=new ArrayList<>();
                    cars.addAll(CarHolder.getInstance().getCarList());

                    outputStream.writeObject(cars);
                    break;
                }
                case "getPurchaseList": {
                    List<Purchase> purchases = PurchaseHolder.getInstance().
                            query(new PurchaseEmployeeIDSpecification(Integer.parseInt(message[1])));
                    outputStream.writeObject(purchases);
                    break;
                }
                case "createPurchase": {
                    int customerID = clientCheck(message);
                    PurchaseHolder.getInstance().add(new Purchase
                            (0, Integer.parseInt(message[4]), customerID, Integer.parseInt(message[5]), message[6]));
                    break;
                }
                case "getRepairList": {
                    List<Repair> repairs = RepairHolder.getInstance().query(
                            new RepairEmployeeIDSpecification(Integer.parseInt(message[1])));
                    outputStream.writeObject(repairs);
                    break;
                }
                case "createRepair": {
                    int customerID = clientCheck(message);
                    RepairHolder.getInstance().add(new Repair(0, Integer.parseInt(message[4]), customerID,
                            message[5], message[6], message[7],Integer.parseInt(message[8]),Integer.parseInt(message[9])));
                    break;
                }
                case "getAllRepairs": {
                    ArrayList<Repair> repairs=new ArrayList<>();
                    repairs.addAll(RepairHolder.getInstance().getRepairList());
                    outputStream.writeObject(repairs);
                    break;
                }
                case "getAllOrders": {
                    ArrayList<Purchase> purchases=new ArrayList<>();
                    purchases.addAll(PurchaseHolder.getInstance().getPurchaseList());
                    outputStream.writeObject(purchases);
                    break;
                }
                case "getAllEmployees": {
                    ArrayList<Employee> employees=new ArrayList<>();
                    employees.addAll(EmployeeHolder.getInstance().getEmployeeList());
                    outputStream.writeObject(employees);
                    break;
                }
                case "getUsers": {
                    ArrayList<User> users=new ArrayList<>();
                    users.addAll(UserHolder.getInstance().getUserList());
                    outputStream.writeObject(users);
                    break;
                }
                case "getCustomers": {
                    ArrayList<Customer> customers=new ArrayList<>();
                    customers.addAll(ClientHolder.getInstance().getCustomerList());
                    outputStream.writeObject(customers);
                    break;
                }
                case "addCar": {
                    Car car=new Car(0, message[1], message[2],
                            Integer.parseInt(message[3]), message[4], Double.parseDouble(message[5]),
                            message[6], Integer.parseInt(message[7]));
                    CarHolder.getInstance().add(car);
                    break;
                }
                case "deleteCar": {
                    List<Purchase> carPurchase=PurchaseHolder.getInstance().
                            query(new PurchaseCarIDSpecification(Integer.parseInt(message[1])));
                    for (Purchase purchase : carPurchase) {
                        PurchaseHolder.getInstance().remove(purchase);
                    }
                    Car car=CarHolder.getInstance().query(new CarSpecification(Integer.parseInt(message[1]))).get(0);
                    CarHolder.getInstance().remove(car);
                    break;
                }
                case "deleteClient":{
                    List<Purchase> clientPurchases=PurchaseHolder.getInstance().
                            query(new PurchaseClientIDSpecification(Integer.parseInt(message[1])));
                    for (Purchase clientPurchase : clientPurchases) {
                        PurchaseHolder.getInstance().remove(clientPurchase);
                    }
                    List<Repair> clientRepair = RepairHolder.getInstance().
                            query(new RepairClientIDSpecification(Integer.parseInt(message[1])));
                    for (Repair repair : clientRepair) {
                        RepairHolder.getInstance().remove(repair);
                    }
                    Customer customer=ClientHolder.getInstance().query(
                            new CustomerIDSpecification(Integer.parseInt(message[1]))).get(0);
                    ClientHolder.getInstance().remove(customer);
                    break;
                }
                case "deleteUser":{
                    Employee employee=EmployeeHolder.getInstance().
                            query(new EmployeeSpecification(Integer.parseInt(message[1]))).get(0);
                    EmployeeHolder.getInstance().remove(employee);
                    User user=UserHolder.getInstance().query(new UserIDSpecification(Integer.parseInt(message[1]))).get(0);
                    UserHolder.getInstance().remove(user);
                    break;
                }
                case "editUser":{
                    if(!message[2].equals("") || !message[3].equals("") || !message[4].equals("")) {
                        User user = UserHolder.getInstance().query(
                                new UserIDSpecification(Integer.parseInt(message[1]))).get(0);
                        String login,pass,type;
                        if(message[2].equals("")){
                            login=user.getLogin();
                        } else {
                            login=message[2];
                        }
                        if(message[3].equals("")){
                            pass=user.getPass();
                        } else {
                            pass=message[3];
                        }
                        if(message[3].equals("")){
                            type=user.getUserType();
                        } else {
                            type=message[4];
                        }
                        User newUser=new User(user.getUserID(),login,pass,type);
                        UserHolder.getInstance().update(newUser,user);
                    }
                    break;
                }
                case "editCustomer":{
                    if(!message[2].equals("") || !message[3].equals("")|| !message[4].equals("")){
                        Customer customer=ClientHolder.getInstance().query(
                                new CustomerIDSpecification(Integer.parseInt(message[1]))).get(0);
                        String fio="",passport="",phone="";
                        if(message[2].equals("")){
                            fio=customer.getFIO();
                        } else {
                            fio=message[2];
                        }
                        if(message[3].equals("")){
                            passport=customer.getPassportNumber();
                        } else {
                            passport=message[3];
                        }
                        if(message[3].equals("")){
                            phone=customer.getPhoneNumber();
                        } else {
                            phone=message[4];
                        }
                        Customer newCustomer=new Customer(customer.getClientID(),fio,passport,phone);
                        ClientHolder.getInstance().update(newCustomer,customer);
                    }
                    break;
                }
                case "editCar": {
                    Car car=CarHolder.getInstance().query(new CarSpecification(Integer.parseInt(message[1]))).get(0);
                    Car newCar=new Car(car.getCarID(),message[2],message[3],Integer.parseInt(message[4]),
                            message[5],Double.parseDouble(message[6]),message[7],Integer.parseInt(message[8]));
                    CarHolder.getInstance().update(newCar,car);
                    break;
                }
                case "editEmployee": {
                    if(!message[2].equals("")||!message[3].equals("")){
                        Employee employee=EmployeeHolder.getInstance().query(
                                new EmployeeIDSpecification(Integer.parseInt(message[1]))).get(0);
                        String fio="";
                        int salary=0;
                        if(message[2].equals("")){
                            fio=employee.getFio();
                        } else {
                            fio=message[2];
                        }
                        if(message[3].equals("")){
                            salary=employee.getSalary();
                        } else {
                            salary=Integer.parseInt(message[3]);
                        }
                        Employee newEmployee=new Employee(employee.getEmployeeID(),fio,salary,employee.getEmployeeUserID());
                        EmployeeHolder.getInstance().update(newEmployee,employee);
                    }
                    break;
                }
                case "deleteEmployee": {
                    List<Purchase> employeePurchase=PurchaseHolder.getInstance().
                            query(new PurchaseEmployeeIDSpecification(Integer.parseInt(message[1])));
                    for (Purchase purchase : employeePurchase) {
                        PurchaseHolder.getInstance().remove(purchase);
                    }
                    List<Repair> employeeRepair=RepairHolder.getInstance().
                            query(new RepairEmployeeIDSpecification(Integer.parseInt(message[1])));
                    for (Repair repair : employeeRepair) {
                        RepairHolder.getInstance().remove(repair);
                    }
                    Employee employee=EmployeeHolder.getInstance().query(
                            new EmployeeIDSpecification(Integer.parseInt(message[1]))).get(0);
                    EmployeeHolder.getInstance().remove(employee);
                    if(!message[2].equals("null")){
                        User user=UserHolder.getInstance().query(
                                new UserIDSpecification(Integer.parseInt(message[2]))).get(0);
                        UserHolder.getInstance().remove(user);
                    }
                    break;
                }
                case "mechChangeStatus": {
                    Repair repair=RepairHolder.getInstance().query(
                            new RepairIDSpecification(Integer.parseInt(message[1]))).get(0);
                    Repair newRepair=new Repair(repair.getRepairID(),repair.getEmployeeID(),repair.getClientID(),
                            repair.getBrand(),repair.getModel(),repair.getRepairType(),1,repair.getValidateByManager());
                    RepairHolder.getInstance().update(newRepair,repair);
                    break;
                }
                case "manChangeStatus": {
                    Repair repair=RepairHolder.getInstance().query(
                            new RepairIDSpecification(Integer.parseInt(message[1]))).get(0);
                    Repair newRepair=new Repair(repair.getRepairID(),repair.getEmployeeID(),repair.getClientID(),
                            repair.getBrand(),repair.getModel(),repair.getRepairType(),repair.getStatus(),1);
                    RepairHolder.getInstance().update(newRepair,repair);
                    break;
                }
                case "deletePurchase": {
                    Purchase purchase=PurchaseHolder.getInstance().query(
                            new PurchaseIDSpecification(Integer.parseInt(message[1]))).get(0);
                    PurchaseHolder.getInstance().remove(purchase);
                    break;
                }
                case "editPurchase": {
                    if (!message[2].equals("") || !message[3].equals("")) {
                        int carID = 0;
                        int clientID = 0;
                        Purchase purchase = PurchaseHolder.getInstance().query(
                                new PurchaseIDSpecification(Integer.parseInt(message[1]))).get(0);
                        if (message[2].equals("")) {
                            carID = purchase.getCarID();
                        } else {
                            carID = Integer.parseInt(message[2]);
                        }
                        if (message[3].equals("")) {
                            clientID = purchase.getClientID();
                        } else {
                            clientID = Integer.parseInt(message[3]);
                        }
                        Purchase newPurchase = new Purchase(purchase.getPurchaseID(), carID, clientID,
                                purchase.getEmployeeID(), purchase.getDate());
                        PurchaseHolder.getInstance().update(newPurchase, purchase);
                    }
                    break;
                }
                case "editRepair": {
                    if(!message[2].equals("") || !message[3].equals("") || !message[4].equals("")){
                        String brand,model,type;
                        Repair repair=RepairHolder.getInstance().query(
                                new RepairIDSpecification(Integer.parseInt(message[1]))).get(0);
                        if(message[2].equals("")){
                            brand=repair.getBrand();
                        } else {
                            brand=message[2];
                        }
                        if(message[3].equals("")){
                            model=repair.getModel();
                        } else {
                            model=message[3];
                        }
                        if(message[4].equals("")){
                            type=repair.getRepairType();
                        } else {
                            type=message[4];
                        }
                        Repair newRepair=new Repair(repair.getRepairID(),repair.getEmployeeID(),repair.getClientID(),
                                brand,model,type,repair.getStatus(),repair.getValidateByManager());
                        RepairHolder.getInstance().update(newRepair,repair);
                    }
                    break;
                }
                case "deleteRepair": {
                    Repair repair=RepairHolder.getInstance().query(
                            new RepairIDSpecification(Integer.parseInt(message[1]))).get(0);
                    RepairHolder.getInstance().remove(repair);
                    break;
                }
                case "newUser":{
                    UserHolder.getInstance().add(new User(0,message[1],message[2],message[3]));
                    User user=UserHolder.getInstance().query(
                            new UserSpecification(message[1],message[2])).get(0);
                    EmployeeHolder.getInstance().add(
                            new Employee(0,message[4],Integer.parseInt(message[5]),user.getUserID()));
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int clientCheck(String[] message) {
        int customerID = 0;
        ClientHolder customerRepository = ClientHolder.getInstance();
        CustomerSpecification customerSpecification = new CustomerSpecification(message[2]);
        List<Customer> customers =
                customerRepository.query(customerSpecification);
        if (customers.size() > 0) {
            customerID = customers.get(0).getClientID();
        } else {
            customerRepository.add(new Customer(0, message[1], message[2], message[3]));
            customers = customerRepository.query(customerSpecification);
            customerID = customers.get(0).getClientID();
        }
        return customerID;
    }

}