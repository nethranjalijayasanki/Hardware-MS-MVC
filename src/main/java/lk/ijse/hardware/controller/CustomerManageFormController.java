package lk.ijse.hardware.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hardware.model.Customer;
import lk.ijse.hardware.model.tm.CustomerTm;
import lk.ijse.hardware.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerManageFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane customerRoot;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTel;

    public void initialize() {

        txtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtTel.requestFocus();
            }
        });

        txtTel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmail.requestFocus();
            }
        });

        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList;
            customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getC_id(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTel(),
                        customer.getEmail()
                );

                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) customerRoot.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String c_id = txtId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(c_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String c_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String email = txtEmail.getText();

        Customer customer = new Customer(c_id, name, address, tel, email);

        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtEmail.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String c_id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String email = txtEmail.getText();

        Customer customer = new Customer(c_id, name, address, tel, email);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String tel = txtTel.getText();

        Customer customer = CustomerRepo.searchByTel(tel);
        if (customer != null) {
            txtId.setText(customer.getC_id());
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtTel.setText(customer.getTel());
            txtEmail.setText(customer.getEmail());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }

    }
}
