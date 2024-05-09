package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {

    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO suppliers VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getS_id());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getCompany());
        pstm.setObject(4, supplier.getTel());
        pstm.setObject(5, supplier.getEmail());

        return pstm.executeUpdate() > 0;
    }

    public static Supplier searchById(String id) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE s_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String s_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String company = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);

            Supplier supplier = new Supplier(s_id, name, company, tel , email);

            return supplier;
        }

        return null;
    }
    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE suppliers SET name = ?, company = ?, tel = ?, email = ? WHERE s_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getCompany());
        pstm.setObject(3, supplier.getTel());
        pstm.setObject(4, supplier.getEmail());
        pstm.setObject(5, supplier.getS_id());

        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE s_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM suppliers";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String s_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String company = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);

            Supplier supplier = new Supplier(s_id, name, company, tel,email);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT s_id FROM suppliers";

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
