package lk.ijse.hardware.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardware.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private AnchorPane dashboardRoot;

    @FXML
    private AnchorPane formRoot;

    @FXML
    private AnchorPane rootLogin;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        String username = txtId.getText();

        checkCredential(password, username);

    }

    private void checkCredential(String password, String username) {
        String sql = "SELECT userId, password from users where userId = ? ";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, username);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()){
                String pw = resultSet.getString("password");

                if (password.equals(pw)){
                    loginDashboard();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Sorry! Your Password is incorrect").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Sorry! Your Username is incorrect").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loginDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) rootLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


