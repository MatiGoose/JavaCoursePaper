package GUI.Mechanic.main;

import Entities.CheckRepair;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import GUI.Logging.LoginController;
import GUI.Main;
import GUI.Mechanic.repair.RepairController;

import java.io.File;
import java.io.IOException;

public class MainMechanicController
{
    private CommandsSender commandsSender;
    @FXML
    private Label mechanicLabel;
    @FXML
    private ImageView mechanicImage;
    @FXML
    private Label mechanicID;
    @FXML
    private TableView<CheckRepair> repairTable;
    @FXML
    private TableColumn<CheckRepair, Integer> repairID;
    @FXML
    private TableColumn<CheckRepair,Integer> mechID;
    @FXML
    private TableColumn<CheckRepair, Integer> clientID;
    @FXML
    private TableColumn<CheckRepair, String> brand;
    @FXML
    private TableColumn<CheckRepair, String> model;
    @FXML
    private TableColumn<CheckRepair, String> repairType;
    @FXML
    private TableColumn<CheckRepair, CheckBox> statusMech;
    @FXML
    private TableColumn<CheckRepair,CheckBox> statusMan;
    @FXML
    private CheckBox stMan;
    @FXML
    private CheckBox stMech;
    @FXML
    private Button createRepair;
    @FXML
    private ImageView logoutImage;
    @FXML
    private ImageView exitImage;

    public void init(String mechanic,String userID){
        mechanicLabel.setText(mechanic);
        //Image image=new Image(new File("images/mechanic.jpg").toURI().toString());
        //mechanicImage.setImage(image);
        mechanicID.setText(userID);
        commandsSender =new CommandsSender();
        //Image logoutImage=new Image(new File("images/logout.png").toURI().toString());
        //this.logoutImage.setImage(logoutImage);
        //Image exitImage=new Image(new File("images/exit.png").toURI().toString());
        //this.exitImage.setImage(exitImage);
    }
    @FXML
    private void getRepaitList(){
        ObservableList<CheckRepair> repairList= FXCollections.observableArrayList(commandsSender.getRepairList());
        for (CheckRepair checkRepair : repairList) {
            if(checkRepair.getStMech().isSelected()){
                checkRepair.getStMech().setDisable(true);
            }
            checkRepair.getStMan().setDisable(true);
        }
        repairID.setCellValueFactory(new PropertyValueFactory<>("repairID"));
        mechID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        clientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        repairType.setCellValueFactory(new PropertyValueFactory<>("repairType"));
        statusMech.setCellValueFactory(new PropertyValueFactory<>("stMech"));
        statusMan.setCellValueFactory(new PropertyValueFactory<>("stMan"));
        repairTable.setItems(repairList);
        EventHandler eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CheckRepair checkRepair=null;
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();

                    for (CheckRepair repair : repairList) {
                        if(chk.equals(repair.getStMech())){
                            checkRepair=repair;
                        }
                    }
                    commandsSender.sendMessage("mechChangeStatus/"+checkRepair.getRepairID());
                    getRepaitList();

                }
            }
        };
        for (CheckRepair checkRepair : repairList) {
            checkRepair.getStMech().setOnAction(eh);
        }
    }

    @FXML private void createRepair(){
        Parent root;
        try {
            RepairController controller;
            FXMLLoader loader = new FXMLLoader(RepairController.class.getResource("newRepair.fxml"));
            Stage orderStage = new Stage();
            root = loader.load();
            orderStage.setScene(new Scene(root, 700, 300));
            controller=loader.getController();
            controller.init();

            orderStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void changeStatus(){

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
    private void closeApp() {
        Platform.exit();
    }
}
