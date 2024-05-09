package lk.ijse.hardware.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardware.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardFormController {

    @FXML
    private AnchorPane dashboardRoot1;

    @FXML
    public Label lblCustomerCount;
    private int customerCount;

    @FXML
    public Label lblOrderCount;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSearch;


    public void initialize() {
        try {
            customerCount = getCustomerCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setCustomerCount(customerCount);
    }
    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;
    }
    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(root1);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/customerManage_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/employeeManage_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/itemManage_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/placeOrder_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/registration_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnTransportOnAction(ActionEvent event) {
        try {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/view/transportManage_form.fxml"));
            dashboardRoot1.getChildren().clear();
            dashboardRoot1.getChildren().add(root1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
