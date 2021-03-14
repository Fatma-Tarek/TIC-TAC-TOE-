/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import gamehandler.ControlView;
import gamehandler.DatabaseHandler;
import help.ComponentHover;
import help.SceneNav;
import help.sound;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class SignupController implements Initializable {

    ArrayList<Integer> errors;
    SceneNav newSceneNav = new SceneNav();
    ControlView view;
    DatabaseHandler dbHandeler = new DatabaseHandler();
    @FXML
    private TextField userName;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField repassword;

    @FXML
    private Button createAccount;

    @FXML
    private Label back;

    @FXML
    private Label nameErrorMess;
    @FXML
    private Label mailErrorMess;
    @FXML
    private Label passErrorMess;
    @FXML
    private Label passErrorMess2;

    private Label[] signupErrorMessges = new Label[4];

    ComponentHover componentHover = new ComponentHover();
    sound newSound = new sound();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        signupErrorMessges[0] = nameErrorMess;
        signupErrorMessges[1] = mailErrorMess;
        signupErrorMessges[2] = passErrorMess;
        signupErrorMessges[3] = passErrorMess2;

        view = new ControlView(signupErrorMessges);

    }

    public void createAccountAction(ActionEvent event) {
        System.out.println(pass.getText());
        //newSound.playMusic("../sound/click-box-check.wav");
        errors = dbHandeler.register(userName.getText(), userEmail.getText(), pass.getText(), repassword.getText());
        if (errors.isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/Login.fxml"));
                Parent root = loader.load();
                newSceneNav.naveTo(root, userName);

                LoginController loginController = new LoginController();
                loginController.getInformationFromSignupPage(userName.getText(), userEmail.getText(), pass.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            for (Integer e : errors) {
                view.showErrorMessages(e);
                System.out.println(e);
            }
        }

    }

    public void backToLoginPageAction(MouseEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        
        System.out.println("SignupAction");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/Login.fxml"));
            Parent root = loader.load();
            newSceneNav.naveTo(root, userName);
            view.hideSignupNameErrorMessage();
            view.hideSignupMailErrorMessage();
            view.hideSignupPassErrorMessage();
            view.hideSignupPass2ErrorMessage();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void BackHoverEnter(MouseEvent event) {

        //  signup.setFill(javafx.scene.paint.Color.valueOf("#FFF"));
        componentHover.TextHoverEnter(back);

    }

    public void BackHoverExit(MouseEvent event) {

        componentHover.TextHoverExit(back);
    }

    public void CreateAccountHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(createAccount);

    }

    public void CreateAccountHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(createAccount);
    }

    public void UserNameHoverEnter(MouseEvent event) {

        componentHover.TextFieldHoverEnter(userName);

    }

    public void UserNameHoverExit(MouseEvent event) {

        componentHover.TextFieldHoverExit(userName);

    }

    public void UserEmailHoverEnter(MouseEvent event) {

        componentHover.TextFieldHoverEnter(userEmail);

    }

    public void UserEmailHoverExit(MouseEvent event) {

        componentHover.TextFieldHoverExit(userEmail);

    }

    public void PasswordHoverEnter(MouseEvent event) {

        componentHover.TextFieldHoverEnter(pass);

    }

    public void PasswordHoverExit(MouseEvent event) {

        componentHover.TextFieldHoverExit(pass);
    }

    public void Password2HoverEnter(MouseEvent event) {

        componentHover.TextFieldHoverEnter(repassword);

    }

    public void Password2HoverExit(MouseEvent event) {

        componentHover.TextFieldHoverExit(repassword);
    }

    public void typeActionSignupName(KeyEvent keyEvent) {
        view.hideSignupNameErrorMessage();
    }

    public void typeActionSignupMail(KeyEvent keyEvent) {
        view.hideSignupMailErrorMessage();
    }

    public void typeActionSignuppass1(KeyEvent keyEvent) {
        view.hideSignupPassErrorMessage();
    }

    public void typeActionSignuppass2(KeyEvent keyEvent) {
        view.hideSignupPass2ErrorMessage();
    }
}
