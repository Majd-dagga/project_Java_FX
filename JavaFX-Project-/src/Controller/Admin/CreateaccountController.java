/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.account;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreateaccountController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn1;
    @FXML
    private Button accountsPageBtn1;
    @FXML
    private Button operationsPageBtn1;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField user_id;
    @FXML
    private TextField accnumber;
    @FXML
    private TextField username;
    @FXML
    private TextField curreny;
    @FXML
    private TextField date;
    @FXML
    private TextField balance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void saveNewUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        
        
          int user_id= Integer.parseInt(this.user_id.getText());
        int accnumber = Integer.parseInt(this.accnumber.getText());
        String username= this.username.getText();
        String curreny = this.curreny.getText();
        double balance = Double.parseDouble(this.balance.getText());
        String date= this.date.getText();
        account accounta = new account(user_id, username, curreny, balance, date);
        accounta.save();
        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
      Controller.Admin.UsersManagmentController.updateStage.close();    
        
    }
    
}
