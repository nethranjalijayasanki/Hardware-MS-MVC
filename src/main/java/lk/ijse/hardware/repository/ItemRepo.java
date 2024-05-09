package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.Employee;
import lk.ijse.hardware.model.Item;

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

    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE items SET qtyOnHand = ?, unitPrice = ?, description = ?, s_id = ? WHERE i_id = ?";

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
            double unitPrice = Double.parseDouble(resultSet.getString(4));
            int qtyOnHand = Integer.parseInt(resultSet.getString(5));

            Item item = new Item(i_id, s_id, description, unitPrice, qtyOnHand);
            itemList.add(item);
        }
        return itemList;

    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT i_id FROM items";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static Item searchByTId(String iId) throws SQLException {
        String sql = "SELECT * FROM items WHERE i_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, iId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String i_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            double unit_price = Double.parseDouble(resultSet.getString(3));
            int qty_on_hand = Integer.parseInt(resultSet.getString(4));
            String s_id = resultSet.getString(5);


            Item item = new Item(i_id,s_id, description, unit_price,qty_on_hand);

            return item;
        }

        return null;
    }
}
