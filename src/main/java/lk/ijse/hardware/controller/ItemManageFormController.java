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
import lk.ijse.hardware.model.Item;
import lk.ijse.hardware.model.Order_Detail;
import lk.ijse.hardware.model.tm.ItemTm;
import lk.ijse.hardware.repository.CustomerRepo;
import lk.ijse.hardware.repository.ItemRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemManageFormController {


    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colI_id;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colS_id;

    @FXML
    private AnchorPane itemRoot;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtI_id;

    @FXML
    private JFXTextField txtS_id;

    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXTextField txtUnitPrice;
    public void initialize() {

        txtI_id.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtS_id.requestFocus();
            }
        });
        txtS_id.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        });

        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtUnitPrice.requestFocus();
            }
        });

        txtUnitPrice.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtQtyOnHand.requestFocus();
            }
        });

        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<Item> itemList;
            itemList = ItemRepo.getAll();
            for (Item item : itemList) {
                ItemTm tm = new ItemTm(
                        item.getI_id(),
                        item.getDescription(),
                        item.getUnit_price(),
                        item.getQty_on_hand(),
                        item.getS_id()
                );

                obList.add(tm);
            }

            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colI_id.setCellValueFactory(new PropertyValueFactory<>("i_id"));
        colS_id.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit Price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty On Hand"));

    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) itemRoot.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String i_id = txtI_id.getText();

        try {
            boolean isDeleted = ItemRepo.delete(i_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String i_id = txtI_id.getText();
        String s_id = txtS_id.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());


        Item item = new Item(i_id, s_id,description, unitPrice, qtyOnHand);

        try {
            boolean isSaved = ItemRepo.save(item);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtI_id.setText("");
        txtS_id.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String i_id = txtI_id.getText();
        String s_id = txtS_id.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());


        Item item = new Item(i_id, s_id,description, unitPrice, qtyOnHand);

        try {
            boolean isUpdated = ItemRepo.update((List<Order_Detail>) item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String i_id = txtI_id.getText();

        Item item = ItemRepo.searchById(i_id);
        if (item != null) {
            txtI_id.setText(item.getI_id());
            txtS_id.setText(item.getS_id());
            txtDescription.setText(item.getDescription());
            txtUnitPrice.setText(String.valueOf(item.getUnit_price()));
            txtQtyOnHand.setText(String.valueOf(item.getQty_on_hand()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
        }
    }

}
