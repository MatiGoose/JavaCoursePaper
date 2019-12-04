package Observer;

import Interfaces.Observer;
import Server.Server;
import Entities.Purchase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseObserver implements Observer<Purchase>
{
    @Override
    public void onAdd(Purchase purchase) {
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder newPurchaseData = new StringBuilder();
        newPurchaseData.append("('"+purchase.getPurchaseID()+"','"+purchase.getCarID()+"','"+
                purchase.getClientID()+"','"+purchase.getEmployeeID()+"','"+purchase.getDate()+"')");
        String add ="INSERT INTO car_showroom.покупки " +
                "(Номер_покупки,Код_Автомобиля, Клиент, ID_Менеджера, Дата) VALUES " + newPurchaseData ;
        try{
            Statement statement=connection.createStatement();
            statement.execute(add);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onDelete(Purchase purchase) {
        String purchaseID="(Номер_покупки='"+purchase.getPurchaseID()+"')";
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String delete="DELETE FROM car_showroom.покупки WHERE "+purchaseID;
        try {
            Statement statement = connection.createStatement();
            statement.execute(delete);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpdate(Purchase newPurchase, Purchase oldPurchase) {
        StringBuilder newPurchaseData=new StringBuilder();
        newPurchaseData.append("Код_Автомобиля='"+newPurchase.getCarID()+"', Клиент='" + newPurchase.getClientID()
                +"', ID_Менеджера='"+newPurchase.getEmployeeID()+"', Дата='"+newPurchase.getDate()+
                "' WHERE (Номер_покупки='" + newPurchase.getPurchaseID()+"')");
        Server server = Server.GetInstance();
        Connection connection = null;
        try {
            connection = server.GetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String update="UPDATE car_showroom.покупки SET " +newPurchaseData;
        try{
            Statement statement = connection.createStatement();
            statement.execute(update);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
