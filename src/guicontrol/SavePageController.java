/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import database.Game;
import database.Move;
import gamehandler.ControlView;
import gamehandler.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import static javafx.stage.Modality.WINDOW_MODAL;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class SavePageController implements Initializable {

    Parent root;
    FXMLLoader loader;
    @FXML
    private Label error;
    @FXML
    private TextField text;
    @FXML
    private Button save;

    @FXML
    private Button cancle;

    ControlView newControlView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        newControlView = new ControlView(error);
    }

    @FXML
    public void saveGameAction(ActionEvent actionEvent) {
        System.out.println("Save Game");

        // ControlGameAction newControlGame = new ControlGameAction();
        // gameSequnce = newControlGame.getGameSequnce();
        System.out.println("DatabaseHandler.loginUser.getId() -> " + DatabaseHandler.loginUser.getId());

        if (!"".equals(text.getText())) {
            if (GameBoardController.isComputer) {
                int gameId = Game.addGame(text.getText(), DatabaseHandler.loginUser.getId(), 1);
                System.out.println(gameId);
                GameBoardController.moves.forEach(move -> Move.addMove(move.getSymbolxo(), move.getLocation(), gameId));
            } else if (GameBoardController.onlineServerFlag) {
                int gameId = Game.addGame(text.getText(), GameBoardController.userID,  GameBoardController.idClient);
                GameBoardController.moves.forEach(move -> Move.addMove(move.getSymbolxo(), move.getLocation(), gameId));
            } else {
                int gameId = Game.addGame(text.getText(), DatabaseHandler.loginUser.getId(), 2);
                GameBoardController.moves.forEach(move -> Move.addMove(move.getSymbolxo(), move.getLocation(), gameId));
            }

            GameBoardController.view.messageToInfo("Game Saved");
            GameBoardController.view.showPlayAgainButton();
            Stage window = (Stage) save.getScene().getWindow();
            window.close();
        }
        else{
           newControlView.showGameNameError();
        }

    }

    @FXML
    public void cancleGameAction(ActionEvent actionEvent) {
        System.out.println("cancle Save Game");

        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/resultPage.fxml"));
            root = loader.load();

            Stage window = (Stage) save.getScene().getWindow();
            window.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            newScene.initOwner(window);
            newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.setResizable(true);

            newScene.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void typeAction(KeyEvent keyEvent) {
        newControlView.hideLoginErrorMessage();
    }

}
