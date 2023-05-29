/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.account;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {

    static account selectedAccountToUpdate;
     public static Stage updateStage;

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<account> accountTableView;
    @FXML
    private TableColumn<account, Integer> id;
    @FXML
    private TableColumn<account, String> Accountnumber;
    @FXML
    private TableColumn<account, String> UserName;
    @FXML
    private TableColumn<account, String> Currency;
    @FXML
    private TableColumn<account, Double> Balance;
    @FXML
    private TableColumn<account, String> CreationDate;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        Accountnumber.setCellValueFactory(new PropertyValueFactory("ACCOUNT"));
        UserName.setCellValueFactory(new PropertyValueFactory("username"));
        Currency.setCellValueFactory(new PropertyValueFactory("currency"));
        Balance.setCellValueFactory(new PropertyValueFactory("BALANCE"));
        CreationDate.setCellValueFactory(new PropertyValueFactory("creation_DATE"));
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneTocreateaccount();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
         ObservableList<account> accountList
                = FXCollections.observableArrayList(account.getAllaccount());
        accountTableView.setItems(accountList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
         if (accountTableView.getSelectionModel().getSelectedItem() != null) {
            //store the selected user from the TableView in our global var user selectedUserToUpdate   
            selectedAccountToUpdate = accountTableView.getSelectionModel().getSelectedItem();
            //load update page fxml
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/updateaccount.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updateUserScene = new Scene(rootUpdate);
            updateStage = new Stage();
            updateStage.setScene(updateUserScene);
            updateStage.setTitle("Update account " + selectedAccountToUpdate.getUsername());
            updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        
         if (this.accountTableView.getSelectionModel().getSelectedItem() != null) {
            //store the selected user from the TableView in new user object
            account selectedaccount = accountTableView.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our User model
                        selectedaccount.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("account deleted");
                    deletedSuccessAlert.setContentText("account deleted");
                    deletedSuccessAlert.show();
                }
            });
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an account");
            warnAlert.setContentText("Please select an account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

}
