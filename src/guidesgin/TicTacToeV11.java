/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guidesgin;

import gamehandler.DatabaseHandler;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.stage.Stage;

/**
 *
 * @author Al-Qirawan
 */
public class TicTacToeV11 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Scene scene = new Scene(root);
        stage.setTitle("Tic Tac Toe ");
        stage.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
        stage.setScene(scene);
        //stage.setResizable(true);
        stage.setOnShown(e->{
            System.out.println("tictactoeexample.TicTacToeGUI.start()");
        });
        
        stage.setOnCloseRequest(e->{
            System.out.println("tictactoeexample.TicTacToeGUI.start()");
            //databaseHandler.updateScore();
        });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
