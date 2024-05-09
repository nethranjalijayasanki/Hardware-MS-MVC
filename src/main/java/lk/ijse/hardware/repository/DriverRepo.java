package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.Driver;
import lk.ijse.hardware.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverRepo {
    public static boolean save(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, driver.getD_id());
        pstm.setObject(2, driver.getName());
        pstm.setObject(3, driver.getTel());
        pstm.setObject(4, driver.getEmail());
        pstm.setObject(5, driver.getWork_time());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Driver driver) throws SQLException {
        String sql = "UPDATE drivers SET name = ?, tel = ?, email = ?, work_time = ? WHERE d_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, driver.getD_id());
        pstm.setObject(2, driver.getName());
        pstm.setObject(3, driver.getTel());
        pstm.setObject(4, driver.getEmail());
        pstm.setObject(5, driver.getWork_time());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM drivers WHERE d_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Driver> getAll() throws SQLException {
        String sql = "SELECT * FROM drivers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Driver> driverList = new ArrayList<>();

        while (resultSet.next()){
            String d_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);
            String work_time = resultSet.getString(4);

            Driver driver = new Driver(d_id, name, tel, email,work_time);
            driverList.add(driver);
        }

        return driverList;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT d_id FROM drivers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public static Driver searchById(String id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE d_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String d_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String email = resultSet.getString(4);
            String work_time = resultSet.getString(5);

            Driver driver = new Driver(d_id, name, tel, email,work_time);

            return driver;
        }

        return null;
    }
}
