/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gamehandler.ControlView;
import gamehandler.DatabaseHandler;
import help.ComponentHover;
import help.SceneNav;
import help.sound;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class LoginController implements Initializable {

    public static int retval = -2;
    Stage s = new Stage();

    private String username;
    private String userEmail;
    private int userScore;

    sound newSound = new sound();
    DatabaseHandler dbHandeler = new DatabaseHandler();
    ComponentHover componentHover = new ComponentHover();
    SceneNav newSceneNav = new SceneNav();

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private Label signup;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginErrorMess;

    ControlView view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println("guicontrol.LoginController.initialize()");

        view = new ControlView(loginErrorMess);

    }

    public void getInformationFromSignupPage(String username, String userEmail, String password) {

        username = username;
        userEmail = userEmail;

    }

    public void closeEvent(WindowEvent e) {
        System.out.println(e.getSource());
        System.out.println("tictactoeexample.LoginController.closeEvent()");
    }

    public void LoginAction(ActionEvent event) {

        //newSound.playMusic("../sound/click.wav");
        System.out.println(email.getText());

        retval = dbHandeler.login(email.getText(), password.getText());

        
        if (retval >= 0) {

            username = dbHandeler.getName();
            userScore = dbHandeler.getScore();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/homePage.fxml"));
                Parent root = loader.load();

                newSceneNav.naveTo(root, email);

                HomePageController homepageController = loader.getController();
                homepageController.setInformationFromLoginPage_(username, Integer.toString(userScore));
                homepageController.setUserId(retval);
                homepageController.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            view.showLoginErrorMessage();
        }

    }

    public void SignupAction(MouseEvent event) {
        System.out.println("SignupAction");

        try {

            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/guidesgin/signup.fxml"));
            Parent root = loader.load();
            newSceneNav.naveTo(root, email);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void SignupHoverEnter(MouseEvent event) {

        //  signup.setFill(javafx.scene.paint.Color.valueOf("#FFF"));
        componentHover.TextHoverEnter(signup);

    }

    public void SignupHoverExit(MouseEvent event) {

        componentHover.TextHoverExit(signup);
    }

    @FXML
    public void LoginHoverEnter(MouseEvent event) {

        System.out.println("guicontrol.LoginController.LoginHoverEnter()");
        componentHover.ButtonHoverEnter(login);

    }

    @FXML
    public void LoginHoverExit(MouseEvent event) {

        System.out.println("guicontrol.LoginController.LoginHoverExit()");
        componentHover.ButtonHoverExit(login);
    }

    public void TextFieldHoverEnter(MouseEvent event) {

        componentHover.TextFieldHoverEnter(email);

    }

    public void TextFieldHoverExit(MouseEvent event) {

        componentHover.TextFieldHoverExit(email);

    }

    public void PasswordHoverEnter(MouseEvent event) {

        componentHover.TextFieldHoverEnter(password);

    }

    public void PasswordHoverExit(MouseEvent event) {

        componentHover.TextFieldHoverExit(password);
    }

    public void typeAction(KeyEvent keyEvent) {
        view.hideLoginErrorMessage();
    }

}
