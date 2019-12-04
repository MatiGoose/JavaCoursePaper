package Observer;

import Interfaces.Observer;
import Server.Server;
import Entities.Repair;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RepairObserver implements Observer<Repair>
{
    @Override
    public void onAdd(Repair repair) {
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder newRepairData = new StringBuilder();
        newRepairData.append("('" + repair.getRepairID() + "','" + repair.getEmployeeID() + "','" + repair.getClientID() + "','" +
                repair.getBrand() + "','" + repair.getModel() + "','" + repair.getRepairType()
                + "','" + repair.getStatus() + "','" +repair.getValidateByManager()+ "')");
        String add = "INSERT into car_showroom.ремонт " +
                "(ID_Ремонта,ID_Механика, ID_Клиента, Марка, Модель, Тип_ремонта, Статус, Проверено_менеджером) " +
                "VALUES " + newRepairData;
        try {
            Statement statement = connection.createStatement();
            statement.execute(add);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDelete(Repair repair) {
        String repairID="(ID_Ремонта='"+repair.getRepairID()+"')";
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String delete="DELETE FROM car_showroom.ремонт where " + repairID;
        try{
            Statement statement=connection.createStatement();
            statement.execute(delete);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdate(Repair newRepair, Repair oldRepair) {
        StringBuilder newRepairData=new StringBuilder();
        newRepairData.append("ID_Механика='"+newRepair.getEmployeeID()+
                "', ID_Клиента='"+newRepair.getRepairID()+
                "', Марка='"+newRepair.getBrand()+"', Модель='"+
                newRepair.getModel()+"', Тип_ремонта='"+newRepair.getRepairType()+
                "', Статус='"+newRepair.getStatus()+"', Проверено_менеджером='"+newRepair.getValidateByManager()+
                "' WHERE (ID_Ремонта='"+newRepair.getRepairID()+"')");
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String update="UPDATE car_showroom.ремонт SET " + newRepairData;
        try{
            Statement statement = connection.createStatement();
            statement.execute(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
