/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;



/**
 *
 * @author Al-Qirawan
 */
public class ComponentHover {

    public void TextHoverEnter(Label text) {

        text.setTextFill(Color.valueOf("#3598db"));
    }

    public void TextHoverExit(Label text) {

       text.setTextFill(Color.valueOf("#777"));
    }

    public void ButtonHoverEnter(Button button) {

        button.setStyle("-fx-background-color: #FFF;-fx-background-radius:10px;-fx-border-radius:10px; -fx-font-size:25;"
                + " -fx-border-color: #3598db; -fx-opacity:0.8;-fx-font-weight:BOLD;");

        button.setTextFill(Color.valueOf("#3598db"));

    }

    public void ButtonHoverExit(Button button) {

        button.setStyle("-fx-background-color: #3598db;-fx-background-radius:10px;-fx-border-radius:10px; -fx-font-size:30;"
                + " -fx-border-color: NONE; -fx-opacity:1.0;-fx-font-weight:BOLD;");
        button.setTextFill(Color.valueOf("#FFF"));
    }

    public void TextFieldHoverEnter(TextField textfield) {

        textfield.setStyle("-fx-background-radius:10px;-fx-border-radius:10px;"
                + "-fx-border-color:#3598db;");

    }

    public void TextFieldHoverExit(TextField textfield) {

        textfield.setStyle("-fx-background-radius:10px;-fx-border-radius:10px;"
                + "-fx-border-color:NONE;");
    }

    public void TextFieldHoverEnter(PasswordField textfield) {

        textfield.setStyle("-fx-background-radius:10px;-fx-border-radius:10px;"
                + "-fx-border-color:#3598db;");

    }

    public void TextFieldHoverExit(PasswordField textfield) {

        textfield.setStyle("-fx-background-radius:10px;-fx-border-radius:10px;"
                + "-fx-border-color:NONE;");
    }

}
