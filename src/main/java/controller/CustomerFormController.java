package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public TextField txtAddress;
    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomers;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    List<Customer> customerList = new ArrayList<>();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");

            preparedStatement.setInt(1,Integer.parseInt(txtId.getText()));
            preparedStatement.setString(2,txtName.getText());
            preparedStatement.setString(3,txtAddress.getText());
            preparedStatement.setDouble(4,Double.parseDouble(txtSalary.getText()));

            if( preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added..!!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadData(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM customer");

            while (resultSet.next()){
                customerList.add(new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadTable();
    }

    private void loadTable(){
        //Link column names and Model class variables
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

//            ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
//            customerList.forEach(customer -> {
//                customerObservableList.add(customer);
//            });

//        tblCustomers.setItems(customerObservableList);

        tblCustomers.setItems(FXCollections.observableArrayList(customerList));

    }


}
