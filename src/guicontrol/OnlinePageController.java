/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import client.ClientHandler;
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
import serverimplementation.ServerImplementation;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class OnlinePageController implements Initializable {

    public static int userId = -1;
    String userName;

    Stage newScene = new Stage();

    SceneNav newSceneNav = new SceneNav();

    ComponentHover newHover = new ComponentHover();

    @FXML
    private Button back;

    @FXML
    private Button host;

    @FXML
    private Button join;

    ServerImplementation newServerImplementation;

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
    public void joinAction(ActionEvent event) {

        System.out.println("joinAction");

        Parent root;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/gameBoard.fxml"));
            root = loader.load();
            Stage window = (Stage) join.getScene().getWindow();
            Stage s = (Stage) window.getOwner();
            s.close();
            newSceneNav.naveTo(root, join);

            GameBoardController gameBoard = loader.getController();
            //GameBoardController gameBoard = new GameBoardController();
            gameBoard.getSecondPlayerInformation(userName);
            gameBoard.setClientPlayerOlineFlag();
            gameBoard.setOlineClientFlag();
            gameBoard.start();
            gameBoard.onlineClientMode();
            gameBoard.setUserID(userId);
            

            gameBoard.joinHandler();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void hostAction(ActionEvent event) {

        System.out.println("hostAction");

        Parent root;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/gameBoard.fxml"));
            root = loader.load();
            Stage window = (Stage) join.getScene().getWindow();
            Stage s = (Stage) window.getOwner();
            s.close();
            newSceneNav.naveTo(root, join);

            GameBoardController gameBoard = loader.getController();
            //GameBoardController gameBoard = new GameBoardController();
            gameBoard.getUserInformation(userName);
            gameBoard.setOlineFlag();
            gameBoard.start();
            gameBoard.onlineServerMode();
            gameBoard.setUserID(userId);

            gameBoard.onlineHandler();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

}
