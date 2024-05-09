package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.Supplier;
import lk.ijse.hardware.model.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportRepo {

    public static boolean save(Transport transport) throws SQLException {
        String sql = "INSERT INTO transports VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, transport.getT_id());
        pstm.setObject(2, transport.getDescription());
        pstm.setObject(3, transport.getD_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Transport transport) throws SQLException {
        String sql = "UPDATE transports SET d_id = ?, description = ? WHERE t_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, transport.getT_id());
        pstm.setObject(2, transport.getDescription());
        pstm.setObject(3, transport.getD_id());

        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM transports WHERE t_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Transport> getAll() throws SQLException {
        String sql = "SELECT * FROM transports";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Transport> transportList = new ArrayList<>();

        while (resultSet.next()) {
            String t_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String d_id = resultSet.getString(3);

            Transport transport = new Transport(t_id, description, d_id);
            transportList.add(transport);
        }
        return transportList;
    }
    public static Transport searchById(String id) throws SQLException {
        String sql = "SELECT * FROM transports WHERE t_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String t_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String d_id = resultSet.getString(3);

            Transport transport = new Transport(t_id, description, d_id);

            return transport;
        }

        return null;
    }
    public static List<String> getId() throws SQLException {
        String sql = "SELECT t_id FROM transports";

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
