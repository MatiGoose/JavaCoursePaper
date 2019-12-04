package Repository;

import Server.Server;
import Entities.Purchase;
import holder.PurchaseHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseRepository
{
    public PurchaseRepository() {

    }

    public void createPurchaseRepository() {
        PurchaseHolder purchaseHolder = PurchaseHolder.getInstance();
        try {
            Statement statement = Server.GetInstance().GetConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from покупки");
            while (resultSet.next()) {
                purchaseHolder.initialize(new Purchase(resultSet.getInt(1)
                        , resultSet.getInt(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
