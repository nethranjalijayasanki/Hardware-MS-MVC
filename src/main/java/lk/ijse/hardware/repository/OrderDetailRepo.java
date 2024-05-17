package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Order_Detail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailRepo {

    public static boolean save(List<Order_Detail> odList) throws SQLException {
        for (Order_Detail od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(Order_Detail od) throws SQLException {
        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, od.getO_id());
        pstm.setString(2, od.getI_id());
        pstm.setInt(3, od.getQty());
        pstm.setDouble(4, od.getUnit_price());

        return pstm.executeUpdate() > 0;    //false ->  |
    }
}
