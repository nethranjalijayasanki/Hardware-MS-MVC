package lk.ijse.hardware.repository;

import lk.ijse.hardware.db.DbConnection;
import lk.ijse.hardware.model.Place_Order;
import java.sql.Connection;
import java.sql.SQLException;
public class PlaceOrderRepo {
    public static boolean placeOrder(Place_Order po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());
            System.out.println("01"+isOrderSaved);
            if (isOrderSaved) {
                boolean isQtyUpdated = ItemRepo.updateQty(po.getOdList());
                System.out.println("02"+isQtyUpdated);
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());
                    System.out.println("03"+isOrderDetailSaved);
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
