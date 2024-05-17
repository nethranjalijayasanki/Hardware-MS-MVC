package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.Employee;
import lk.ijse.hardware.model.Item;
import lk.ijse.hardware.model.Order_Detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {

    public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO items VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, item.getI_id());
        pstm.setObject(2, item.getS_id());
        pstm.setObject(3, item.getDescription());
        pstm.setObject(4, item.getUnit_price());
        pstm.setObject(5, item.getQty_on_hand());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM items WHERE i_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM items";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Item> itemList = new ArrayList<>();

        while (resultSet.next()) {
            String i_id = resultSet.getString(1);
            String s_id = resultSet.getString(2);
            String description = resultSet.getString(3);
            double unitPrice = resultSet.getDouble(4);
            int qtyOnHand = resultSet.getInt(5);

            Item item = new Item(i_id, s_id, description, unitPrice, qtyOnHand);
            itemList.add(item);
        }
        return itemList;
    }

    public static Item searchById(String id) throws SQLException {
        String sql = "SELECT * FROM items WHERE i_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            );
        }
        return null;

    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT i_id FROM items";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;

    }
    public static boolean update(List<Order_Detail> odList) throws SQLException {
        for (Order_Detail od : odList) {
            boolean isUpdateQty = updateQty(odList);
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    public static boolean updateQty(List<Order_Detail> odList) throws SQLException {
        String sql = "UPDATE items SET qty_on_hand = qty_on_hand - ? WHERE i_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (Order_Detail od : odList){
            pstm.setInt(1, Integer.parseInt(od.getI_id()));
            pstm.setString(2, String.valueOf(od.getQty()));
            pstm.executeUpdate();
        }
        return true;
    }
}
