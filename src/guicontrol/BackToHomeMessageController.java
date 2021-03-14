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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class BackToHomeMessageController implements Initializable {

    String userName;
    String userScore;

    @FXML
    private Button yes;

    @FXML
    private Button no;

    SceneNav sceneNav = new SceneNav();
    ComponentHover componentHover = new ComponentHover();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void yesBtnAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/homePage.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) yes.getScene().getWindow();
            Stage s = (Stage) window.getOwner();
            s.close();
            sceneNav.naveTo(root, yes);

            HomePageController homepageController = loader.getController();
            homepageController.setInformationFromLoginPage(userName, userScore);
            homepageController.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void noBtnAction(ActionEvent event) {

        Stage window = (Stage) yes.getScene().getWindow();

        window.close();

    }

    @FXML
    public void YesHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(yes);

    }

    @FXML
    public void YesHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(yes);
    }

    @FXML
    public void NoHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(no);

    }

    @FXML
    public void NOHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(no);
    }

    public void setUserInfo(String score, String userName) {

        this.userScore = score;
        this.userName = userName;
    }

}
