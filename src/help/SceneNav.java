/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import gamehandler.DatabaseHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Al-Qirawan
 */
public class SceneNav {

    public void naveTo(Parent root, TextField userName) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Stage window = (Stage) userName.getScene().getWindow();

        window.close();

        window.setScene(new Scene(root));

        window.setTitle("Tic Tac Toe Aplication");
        //window.setResizable(true);
        window.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
        window.show();
        window.setOnCloseRequest(e -> {
            System.out.println("Egila");
           // databaseHandler.updateScore();
        });

    }

    public void onClose() {

    }

        
         public void naveTo(Parent root, ListView listView) {

        Stage window = (Stage) listView.getScene().getWindow();
        window.close();

        window.setScene(new Scene(root));

        window.setTitle("Tic Tac Toe Aplication");
        window.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
        window.setResizable(true);

        window.show();

    }
    public void naveTo(Parent root, Label userName) {

        Stage window = (Stage) userName.getScene().getWindow();
        window.close();

        window.setScene(new Scene(root));

        window.setTitle("Tic Tac Toe Aplication");
        window.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
        window.setResizable(true);

        window.show();

    }

    public void naveTo(Parent root, Button btn) {

        Stage window = (Stage) btn.getScene().getWindow();
        window.close();

        window.setScene(new Scene(root));
        window.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
        window.setTitle("Tic Tac Toe Aplication");
        window.setResizable(true);

        window.show();

    }

}
