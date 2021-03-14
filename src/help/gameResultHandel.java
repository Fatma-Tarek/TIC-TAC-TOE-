/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import guicontrol.resultPageController;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import static javafx.stage.Modality.WINDOW_MODAL;
import javafx.stage.Stage;

/**
 *
 * @author Al-Qirawan
 */
public class gameResultHandel {

    boolean computerFlag;
    boolean playerTurn;
    String playerOneName;
    String playerTwoName;
    String userScore;
    Label userName;

    public void resultMessage(Label userName, int status) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/resultPage.fxml"));
            Parent root = loader.load();

            Stage window = (Stage) userName.getScene().getWindow();
            //window.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            newScene.initOwner(window);
            newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.setResizable(true);

            newScene.show();

            resultPageController newMessage = loader.getController();
            newMessage.playerInfo(computerFlag, playerOneName, playerTwoName, userScore);
            newMessage.messageInfo(status);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void playerInfo(boolean computerFlag, String playerOneName, String playerTwoName, String userScore) {
        this.computerFlag = computerFlag;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.userScore = userScore;
    }

}
