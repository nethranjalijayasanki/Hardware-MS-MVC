package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getC_id());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getTel());
        pstm.setObject(5, customer.getEmail());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, address = ?, tel = ?, email = ? WHERE c_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getTel());
        pstm.setObject(4, customer.getEmail());
        pstm.setObject(5, customer.getC_id());

        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customers WHERE c_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customers";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String c_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);

            Customer customer = new Customer(c_id, name, address, tel,email);
            cusList.add(customer);
        }
        return cusList;
    }

    public static Customer searchByTel(String tell) throws SQLException {
        String sql = "SELECT * FROM customers WHERE tel = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, tell);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String c_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);

            Customer customer = new Customer(c_id, name, address, tel, email);

            return customer;
        }

        return null;
    }
    public static List<String> getId() throws SQLException {
        String sql = "SELECT c_id FROM customer";

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
