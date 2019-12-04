package GUI.Manager.main;

import Entities.*;
import GUI.Error.Error;
import GUI.Manager.car.carController;
import GUI.Manager.customerEditor.CustomerEditorController;
import GUI.Manager.employee.EmployeeController;
import GUI.Manager.employeeEditor.employeeEditorController;
import GUI.Manager.order.OrderController;
import GUI.Manager.orderEditor.OrderEditorController;
import GUI.Manager.repairEditor.repairEditorController;
import GUI.Manager.userEditor.UserEditorController;
import Server.CommandsSender;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import GUI.Logging.LoginController;
import GUI.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MainManagerController
{
    private String userType;
    private CommandsSender commandsSender;
    @FXML
    private Button addCarButton;
    @FXML
    private MenuItem addCarMenu;
    @FXML
    private Label managerLabel;
    @FXML
    private ImageView managerImage;
    @FXML
    private Label managerID;
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, Integer> carCode;
    @FXML
    private TableColumn<Car, String> carBrand;
    @FXML
    private TableColumn<Car, String> carModel;
    @FXML
    private TableColumn<Car, Integer> carYear;
    @FXML
    private TableColumn<Car, String> carEngineType;
    @FXML
    private TableColumn<Car, Double> carEngineCapacity;
    @FXML
    private TableColumn<Car, String> carColor;
    @FXML
    private TableColumn<Car, Integer> carPrice;
    @FXML
    private TableView<Purchase> orderTable;
    @FXML
    private TableColumn<Purchase, Integer> purchaseNumber;
    @FXML
    private TableColumn<Purchase, Integer> carID;
    @FXML
    private TableColumn<Purchase, Integer> clientID;
    @FXML
    private TableColumn<Purchase, Integer> employeeID;
    @FXML
    private TableColumn<Purchase, String> purchaseDate;
    @FXML
    private TableView<CheckRepair> repairTable;
    @FXML
    private TableColumn<CheckRepair, Integer> repairID;
    @FXML
    private TableColumn<CheckRepair,Integer> mechID;
    @FXML
    private TableColumn<CheckRepair, Integer> repairClientID;
    @FXML
    private TableColumn<CheckRepair, String> repairBrand;
    @FXML
    private TableColumn<CheckRepair, String> repairModel;
    @FXML
    private TableColumn<CheckRepair, String> repairType;
    @FXML
    private TableColumn<CheckRepair, CheckBox> statusMech;
    @FXML
    private TableColumn<CheckRepair,CheckBox> statusMan;
    @FXML
    private Tab employeeTab;
    @FXML
    private Tab userTab;
    @FXML
    private Tab repairTab;
    @FXML
    private Tab statsTab;
    @FXML
    private Tab clientTab;
    @FXML
    private Button getAllOrdersButton;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> emplID;
    @FXML
    private TableColumn<Employee,String> emplFIO;
    @FXML
    private TableColumn<Employee, Integer> emplSalary;
    @FXML
    private TableColumn<Employee, Integer> emplUserID;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> userID;
    @FXML
    private TableColumn<User, String>  userLogin;
    @FXML
    private TableColumn<User, String>  userPass;
    @FXML
    private TableColumn<User, String> userUserType;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerID;
    @FXML
    private TableColumn<Customer, String> customerFIO;
    @FXML
    private TableColumn<Customer, String> customerPassport;
    @FXML
    private TableColumn<Customer, String> customerPhone;
    @FXML
    private Button deleteCarButton;
    @FXML
    private MenuItem deleteCarMenu;
    @FXML
    private Button editCarButton;
    @FXML
    private Button orderCreateOrderButton;
    @FXML
    private MenuItem createOrderMenu;
    @FXML
    private PieChart chart;
    @FXML
    private ChoiceBox<String> statsChoice;
    @FXML
    private TextArea txtArea;
    @FXML
    private ImageView logoutImage;
    @FXML
    private ImageView exitImage;
    @FXML
    private ChoiceBox<String> diagramChoice;







    public void init(String manager, String id) {
        commandsSender = new CommandsSender();
        managerLabel.setText(manager);
        userType=manager.split(":")[0];
        //Image image=null;
        //Image logoutImage=new Image(new File("images/logout.png").toURI().toString());
        //Image exitImage=new Image(new File("images/exit.png").toURI().toString());
        //this.logoutImage.setImage(logoutImage);
        //this.exitImage.setImage(exitImage);
        if(userType.equals("Менеджер")) {
            //image = new Image(new File("images/manager.jpg").toURI().toString());
            addCarButton.setVisible(false);
            addCarMenu.setVisible(false);
            employeeTab.setDisable(true);
            userTab.setDisable(true);
            statsTab.setDisable(true);
            //clientTab.setDisable(true);
            getAllOrdersButton.setVisible(false);
            deleteCarButton.setVisible(false);
            deleteCarMenu.setVisible(false);
            editCarButton.setVisible(false);


        } else {
            //image = new Image(new File("images/admin.jpg").toURI().toString());
        }
        //managerImage.setImage(image);
        managerID.setText(id);
        purchaseNumber.setCellValueFactory(new PropertyValueFactory<>("purchaseID"));
        carID.setCellValueFactory(new PropertyValueFactory<>("carID"));
        clientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        purchaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        getCarList();
        getOrderList();
        getRepairList();
        getCustomers();
        if(userType.equals("Админ")){
            getAllOrders();
            getUsers();

            getEmployees();
        }
        ObservableList<String> strings= FXCollections.observableArrayList("за предыдущий месяц","за текущий год");
        statsChoice.setItems(strings);
        ObservableList<String> diagrams=FXCollections.observableArrayList("доступные автомобили","проданные автомобили");
        diagramChoice.setItems(diagrams);

    }

    @FXML
    private void getCarList() {
        List<Car> tmp = commandsSender.getCarList();
        ObservableList<Car> carList = FXCollections.observableArrayList(tmp);
        //ObservableList<Car> carList = FXCollections.observableArrayList(
        //        commandsSender.getCarList());
        carCode.setCellValueFactory(new PropertyValueFactory<>("carID"));
        carBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        carModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        carYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        carEngineType.setCellValueFactory(new PropertyValueFactory<>("engineType"));
        carEngineCapacity.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        carColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        carPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        carTable.setItems(carList);



    }
    @FXML
    private void getBrandStats(){
        if(diagramChoice.getValue()==null){
            Error.showAlert("Вы не выбрали данные для построения дигараммы");
            return;
        }
        ObservableList<Purchase> purchaseList=FXCollections.observableArrayList(commandsSender.getAllPurchases());
        ObservableList<Car> carList = FXCollections.observableArrayList(
                commandsSender.getCarList());
        if(diagramChoice.getValue().equals("проданные автомобили")) {
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
            HashMap<String, Integer> cars = new HashMap<>();
            int id = 0;
            String brand = "";
            for (Purchase purchase : purchaseList) {
                id = purchase.getCarID();
                for (Car car : carList) {
                    if (car.getCarID() == id) {
                        brand = car.getBrand();
                    }
                }
                if (cars.containsKey(brand)) {
                    cars.put(brand, cars.get(brand) + 1);
                } else {
                    cars.put(brand, 1);
                }
            }
            for (Map.Entry<String, Integer> entry : cars.entrySet()) {
                data.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
            chart.setData(data);
        } else {
            HashMap<String, Integer> cars=new HashMap<>();
            String brand = "";
            for (Car car : carList) {
                brand=car.getBrand();
                if (cars.containsKey(brand)) {
                    cars.put(brand, cars.get(brand) + 1);
                } else {
                    cars.put(brand, 1);
                }
            }
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : cars.entrySet()) {
                data.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
            chart.setData(data);
        }
    }
    @FXML
    private void writeStatsToFile(){
        txtArea.setText("");
        if(statsChoice.getValue()==null){
            Error.showAlert("Вы не выбрали период для отбора статистики");
            return;
        }
        ObservableList<Purchase> purchaseList=FXCollections.observableArrayList(commandsSender.getAllPurchases());
        ObservableList<Car> carList = FXCollections.observableArrayList(
                commandsSender.getCarList());
        ArrayList<String> arrayList=new ArrayList<>();
        String brand="";
        int price=0,commonPrice=0;
        Calendar calendar=new GregorianCalendar();
        int month=0;
        if(calendar.get(Calendar.MONTH)==0){
            month=12;
        } else {
            month=calendar.get(Calendar.MONTH);
        }
        arrayList.add("ID  Марка       Дата          Цена");
        txtArea.appendText("ID  Марка       Дата          Цена\n");
        for (Purchase purchase : purchaseList) {
            String[] dateString=purchase.getDate().split("-");
            if(statsChoice.getValue().equals("за предыдущий месяц")){
                if(Integer.parseInt(dateString[1])!=month){
                    continue;
                }

            } else if(statsChoice.getValue().equals("за текущий год")){
                if(Integer.parseInt(dateString[0])!=calendar.get(Calendar.YEAR)){
                    continue;
                }

            }
            for (Car car : carList) {
                if(car.getCarID()==purchase.getCarID()){
                    brand=car.getBrand();
                    price=car.getPrice();
                    break;
                }
            }
            String string=purchase.getPurchaseID()+"    "+brand+"    "+purchase.getDate()+"    "+price;
            txtArea.appendText(string+"\n");
            commonPrice+=price;
            arrayList.add(string);

        }
        String str="Общая прибыль с продажи автомобилей: "+commonPrice;
        arrayList.add(str+"\n");
        txtArea.appendText(str);
        Path out = Paths.get("stats.txt");
        try {
            Files.write(out, arrayList);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void getOrderList(){
        ObservableList<Purchase> purchaseList=FXCollections.observableArrayList(commandsSender.getPurchaseList());
        orderTable.setItems(purchaseList);
    }

    @FXML
    private void getAllOrders(){
        ObservableList<Purchase> purchaseList=FXCollections.observableArrayList(commandsSender.getAllPurchases());
        orderTable.setItems(purchaseList);

    }

    @FXML
    private void getUsers(){
        ObservableList<User> users=FXCollections.observableArrayList(commandsSender.getUsers());
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        userPass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        userUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        userTable.setItems(users);
    }
    @FXML
    private void getCustomers(){
        ObservableList<Customer> customers=FXCollections.observableArrayList(commandsSender.getCustomers());
        customerID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        customerFIO.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        customerPassport.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerTable.setItems(customers);
    }

    @FXML
    private void getRepairList(){
        ObservableList<CheckRepair> repairs= FXCollections.observableArrayList(commandsSender.getAllRepairs());
        for (CheckRepair repair : repairs) {
            repair.getStMech().setDisable(true);
            if(!repair.getStMech().isSelected() || (repair.getStMech().isSelected() && repair.getStMan().isSelected())){
                repair.getStMan().setDisable(true);
            }
        }
        repairID.setCellValueFactory(new PropertyValueFactory<>("repairID"));
        mechID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        repairClientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        repairBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        repairModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        repairType.setCellValueFactory(new PropertyValueFactory<>("repairType"));
        statusMech.setCellValueFactory(new PropertyValueFactory<>("stMech"));
        statusMan.setCellValueFactory(new PropertyValueFactory<>("stMan"));
        repairTable.setItems(repairs);
        EventHandler eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CheckRepair checkRepair=null;
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();

                    for (CheckRepair repair : repairs) {
                        if(chk.equals(repair.getStMan())){
                            checkRepair=repair;
                        }
                    }
                    commandsSender.sendMessage("manChangeStatus/"+checkRepair.getRepairID());
                    getRepairList();

                }
            }
        };
        for (CheckRepair checkRepair : repairs) {
            checkRepair.getStMan().setOnAction(eh);
        }


    }
    @FXML void getEmployees (){
        ObservableList<Employee> employees=FXCollections.observableArrayList(commandsSender.getEmployees());
        emplID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        emplFIO.setCellValueFactory(new PropertyValueFactory<>("fio"));
        emplSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        emplUserID.setCellValueFactory(new PropertyValueFactory<>("employeeUserID"));
        employeeTable.setItems(employees);
    }
    @FXML
    private void editOrder(){
        Purchase purchase=orderTable.getSelectionModel().getSelectedItem();
        if(purchase==null){
            Error.showAlert("Вы не выбрали заказ для редактирования");
            return;
        }
        Parent root;
        try {
            OrderEditorController orderEditController;
            FXMLLoader loader=new FXMLLoader((OrderEditorController.class.getResource("orderEditor.fxml")));
            Stage orderEditStage=new Stage();
            root=loader.load();
            orderEditStage.setScene(new Scene(root,500,200));
            orderEditController=loader.getController();
            orderEditController.init(purchase);
            orderEditStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void editEmployee(){
        Employee employee=employeeTable.getSelectionModel().getSelectedItem();
        if(employee==null){
            Error.showAlert("Вы не выбрали сотрудника для редактирования");
            return;
        }
        Parent root;
        try{
            employeeEditorController employeeEditController;
            FXMLLoader loader=new FXMLLoader(employeeEditorController.class.getResource("employeeEditor.fxml"));
            Stage employeeStage=new Stage();
            root=loader.load();
            employeeStage.setScene(new Scene(root,500,200));
            employeeEditController=loader.getController();
            employeeEditController.init(employee);
            employeeStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void editUser(){
        User user=userTable.getSelectionModel().getSelectedItem();
        if(user==null){
            Error.showAlert("Вы не выбрали пользователя для редактирования");
            return;
        }
        Parent root;
        try{
            UserEditorController userEditController;
            FXMLLoader loader=new FXMLLoader(UserEditorController.class.getResource("userEditor.fxml"));
            Stage userStage=new Stage();
            root=loader.load();
            userStage.setScene(new Scene(root,500,200));
            userEditController=loader.getController();
            userEditController.init(user);
            userStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    @FXML
    private void editRepair(){
        CheckRepair repair=repairTable.getSelectionModel().getSelectedItem();
        if(repair==null){
            Error.showAlert("Вы не выбрали запись для редактирования");
            return;
        }
        Parent root;
        try{
            repairEditorController repairEditController;
            FXMLLoader loader=new FXMLLoader(repairEditorController.class.getResource("repairEditor.fxml"));
            Stage stage=new Stage();
            root=loader.load();
            stage.setScene(new Scene(root,500,250));
            repairEditController=loader.getController();
            repairEditController.init(repair);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createOrder(ActionEvent ev){
        Car car= carTable.getSelectionModel().getSelectedItem();
        if(car==null && ev.getSource()!=orderCreateOrderButton && ev.getSource()!=createOrderMenu){
            Error.showAlert("Вы не выбрали автомобиль");
            return;
        }
        Parent root;
        try {
            OrderController orderController;
            FXMLLoader loader = new FXMLLoader(OrderController.class.getResource("order.fxml"));
            Stage orderStage = new Stage();
            root = loader.load();
            orderStage.setScene(new Scene(root, 700, 300));
            orderController=loader.getController();
            if(ev.getSource()==orderCreateOrderButton){
                orderController.init(0);
            } else {
                orderController.init(car.getCarID());
            }
            orderStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void deleteCar(){
        Car car= carTable.getSelectionModel().getSelectedItem();
        if(car==null){
            Error.showAlert("Вы не выбрали автомобиль для удаления");
            return;
        }
        int answer= Error.showConfirmationAlert("Вы дейсвительно хотите удалить автомобиль");
        if(answer == 0){
            return;
        }
        commandsSender.sendMessage("deleteCar/"+car.getCarID());
        getCarList();
    }

    @FXML
    private void deleteClient(){
        Customer customer=customerTable.getSelectionModel().getSelectedItem();
        if (customer == null) {
            Error.showAlert("Вы не выбрали клиента для удаления");
            return;
        }
        int answer= Error.showConfirmationAlert("Вы дейсвительно хотите удалить клиента?");
        if(answer == 0){
            return;
        }
        commandsSender.sendMessage("deleteClient/"+customer.getClientID());
        getCustomers();

    }
    @FXML
    private void editCustomer(){
        Customer customer=customerTable.getSelectionModel().getSelectedItem();
        if (customer == null) {
            Error.showAlert("Вы не выбрали клиента для редактирования");
            return;
        }
        Parent root;
        try {
            CustomerEditorController customerEditController;
            FXMLLoader loader=new FXMLLoader(CustomerEditorController.class.getResource("customerEdit.fxml"));
            Stage userEdit=new Stage();
            root=loader.load();
            userEdit.setScene(new Scene(root,500,200));
            customerEditController=loader.getController();
            customerEditController.init(customer);
            userEdit.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void deleteUser(){
        User user=userTable.getSelectionModel().getSelectedItem();
        if(user==null){
            Error.showAlert("Вы не выбрали пользователя для удаления");
            return;
        }
        int answer= Error.showConfirmationAlert("Вы дейсвительно хотите удалить пользователя?");
        if(answer == 0){
            return;
        }
        commandsSender.sendMessage("deleteUser/"+user.getUserID());
        getUsers();
    }
    @FXML
    private void deleteRepair(){
        CheckRepair repair=repairTable.getSelectionModel().getSelectedItem();
        if(repair==null){
            Error.showAlert("Вы не выбрали запись для удаления");
            return;
        }
        int answer= Error.showConfirmationAlert("Вы дейсвительно хотите удалить запись о ремонте?");
        if(answer == 0){
            return;
        }
        commandsSender.sendMessage("deleteRepair/"+repair.getRepairID());
        getCarList();
    }
    @FXML
    private void deleteOrder(){
        Purchase purchase=orderTable.getSelectionModel().getSelectedItem();
        if(purchase==null){
            Error.showAlert("Вы не выбрали заказ для удаления");
            return;
        }
        int answer = Error.showConfirmationAlert("Вы дейсвительно хотите удалить заказ?");
        if(answer==0){
            return;
        }
        commandsSender.sendMessage("deletePurchase/"+purchase.getPurchaseID());
        getOrderList();
    }
    @FXML
    private void deleteEmployee(){
        Employee employee=employeeTable.getSelectionModel().getSelectedItem();
        if(employee==null){
            Error.showAlert("Вы не выбрали сотрудника");
            return;
        }
        int answer = Error.showConfirmationAlert("Вы дейсвительно хотите удалить сотрудника?");
        if(answer==0){
            return;
        }
        commandsSender.sendMessage("deleteEmployee/"+employee.getEmployeeID()+"/"+employee.getEmployeeUserID());
        getEmployees();
    }
    @FXML
    private void createEmployee(){
        Parent root;
        try{
            EmployeeController employeeController;
            FXMLLoader loader=new FXMLLoader(EmployeeController.class.getResource("employee.fxml"));
            Stage employeeStage=new Stage();
            root=loader.load();
            employeeStage.setScene(new Scene(root,700,300));
            employeeController=loader.getController();
            employeeController.init();
            employeeStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    private void createCar(ActionEvent ev){
        Car car=null;
        Parent root;
        try {
            carController carController;
            FXMLLoader loader = new FXMLLoader(carController.class.getResource("car.fxml"));
            Stage carStage = new Stage();
            root=loader.load();
            carStage.setScene(new Scene(root,700,300));
            carController=loader.getController();
            if(ev.getSource()==addCarButton || ev.getSource()==addCarMenu ){
                carController.init(car);
            } else {
                car= carTable.getSelectionModel().getSelectedItem();
                if(car==null){
                    Error.showAlert("Вы не выбрали автомобиль");
                    return;
                }
                carController.init(car);
            }

            carStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void logout(){
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("sample.fxml"));
            root = loader.load();
            Main.getMainStage().setScene(new Scene(root, 466, 300));
            Client.Client.getInstance().setClientSocket(null);
        } catch (IOException e){
            e.printStackTrace();
        }


    }
    @FXML
    private void closeApp()
    {
        Client.Client.getInstance().setClientSocket(null);
        Platform.exit();
    }
}
