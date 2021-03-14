/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import help.ComponentHover;
import help.SceneNav;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class SelectLevelMessageController implements Initializable {

    public static int userID = -1;

    String userName;

    Stage newScene = new Stage();

    Parent root;
    FXMLLoader loader;

    SceneNav newSceneNav = new SceneNav();

    ComponentHover newHover = new ComponentHover();

    @FXML
    private Button back;

    @FXML
    private Button play;

    @FXML
    private Button easy;

    @FXML
    private Button hard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void backAction(ActionEvent event) {

        Stage window = (Stage) back.getScene().getWindow();

        window.close();

    }

    @FXML
    public void playAction(ActionEvent event) {

        System.out.println(userName);

    }

    @FXML
    public void easyAction(ActionEvent event) {

        System.out.println(userName);

        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/gameBoard.fxml"));
            root = loader.load();
            Stage window = (Stage) easy.getScene().getWindow();
            Stage s = (Stage) window.getOwner();
            s.close();
            newSceneNav.naveTo(root, easy);

            GameBoardController gameBoard = loader.getController();
            gameBoard.getUserInformation(userName);
            gameBoard.getSecondPlayerInformation("Computer");
            gameBoard.changeComuterFlag();
            gameBoard.resetHardFlag();
            gameBoard.setUserID(userID);
            gameBoard.computerMode();
            gameBoard.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void hardAction(ActionEvent event) {

        System.out.println(userName);

        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/gameBoard.fxml"));
            root = loader.load();
            Stage window = (Stage) easy.getScene().getWindow();
            Stage s = (Stage) window.getOwner();
            s.close();
            newSceneNav.naveTo(root, hard);
            GameBoardController gameBoard = loader.getController();
            gameBoard.getUserInformation(userName);
            gameBoard.getSecondPlayerInformation("Computer");
            gameBoard.changeComuterFlag();
            gameBoard.setHardFlag();
            gameBoard.setUserID(userID);
            gameBoard.computerMode();
            gameBoard.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public void setUserID(int id) {

        this.userID = id;
    }

}
