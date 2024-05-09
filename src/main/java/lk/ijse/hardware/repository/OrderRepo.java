package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, order.getO_id());
        pstm.setObject(2, order.getName());
        pstm.setObject(3, order.getQty_on_hand());
        pstm.setObject(4, order.getPrice());
        pstm.setObject(5, order.getC_id());
        pstm.setObject(6, order.getS_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Order order) throws SQLException {
        String sql = "UPDATE orders SET name = ?, qty_on_hand = ? ,  price = ?, c_id = ?, s_id = ? WHERE o_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, order.getO_id());
        pstm.setObject(2, order.getName());
        pstm.setObject(3, order.getQty_on_hand());
        pstm.setObject(4, order.getPrice());
        pstm.setObject(5, order.getC_id());
        pstm.setObject(6, order.getS_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM orders WHERE o_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Order> getAll() throws SQLException {
        String sql = "SELECT * FROM orders";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Order> orderList = new ArrayList<>();

        while (resultSet.next()) {
            String o_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String c_id = resultSet.getString(3);
            String t_id = resultSet.getString(4);
            int qtyOnHand = Integer.parseInt(resultSet.getString(5));
            double price = Double.parseDouble(resultSet.getString(6));

            Order order = new Order(o_id, name, qtyOnHand, price, c_id, t_id);
            orderList.add(order);
        }
        return orderList;
    }

    public static Order searchByTel(int tell) throws SQLException {
        String sql = "SELECT * FROM customers WHERE tel = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, tell);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String o_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qtyOnHand = Integer.parseInt(resultSet.getString(3));
            double price = Double.parseDouble(resultSet.getString(4));
            String c_id = resultSet.getString(5);
            String s_id = resultSet.getString(6);

            Order order = new Order(o_id, name, qtyOnHand, price, c_id,s_id);

            return order;
        }

        return null;
    }
    public static List<String> getId() throws SQLException {
        String sql = "SELECT o_id FROM orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
