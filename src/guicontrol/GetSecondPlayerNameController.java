/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import gamehandler.ControlView;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class GetSecondPlayerNameController implements Initializable {

    public static int useId = 0;
    String name;
    String newScore;
    HomePageController homeData = new HomePageController();
    ControlView view;
    SceneNav sceneNav = new SceneNav();
    @FXML
    private TextField plyerName;

    @FXML
    private Label secondPlayerNameErrorMess;

    @FXML
    private Button ok;
    @FXML
    private Button cancleBtn;

    //private Label userName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        view = new ControlView(secondPlayerNameErrorMess);
    }

    public void getUserInformation(String userName) {

        name = userName;

    }

    public void getUserInformation(String userName, String score) {

        name = userName;
        newScore = score;
    }
    
      public void setUserID(int id) {

        this.useId = id;
    }


    public void test(Label userName) {

        userName = userName;

    }

    @FXML
    public void okBtn(ActionEvent event) {

        try {
            System.out.println(plyerName.getText());

            if (!"".equals(plyerName.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/gameBoard.fxml"));
                Parent root = loader.load();
                Stage window = (Stage) ok.getScene().getWindow();
                Stage s = (Stage) window.getOwner();
                s.close();
                sceneNav.naveTo(root, plyerName);

                GameBoardController gameBoard = loader.getController();
                gameBoard.getSecondPlayerInformation(plyerName.getText());

                System.out.println("Here " + name);
                gameBoard.getUserInformation(name, newScore);
                gameBoard.start();
                gameBoard.offlineMode();
                gameBoard.setUserID(useId);

            } else {

                view.showLoginErrorMessage();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void cancleBtn(ActionEvent event) {

        Stage window = (Stage) plyerName.getScene().getWindow();

        window.close();

    }

    @FXML
    public void typeAction(KeyEvent keyEvent) {
        view.hideLoginErrorMessage();
    }
}
