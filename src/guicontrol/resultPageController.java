/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import database.User;
import gamehandler.DatabaseHandler;
import help.ComponentHover;
import help.SceneNav;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static javafx.stage.Modality.WINDOW_MODAL;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class resultPageController implements Initializable {

    Parent root;
    FXMLLoader loader;
    String gameName = "GameTest";

    int[] gameSequnce = new int[9];

    boolean computerFlag;
    boolean playerTurn;
    String playerOneName;
    String playerTwoName;
    String userScore;

    private ComponentHover componentHover = new ComponentHover();
    private SceneNav newSceneNav = new SceneNav();
    Random rand = new Random();
    GameBoardController newGameBoard = new GameBoardController();
    @FXML
    private Button playAgain;

    @FXML
    private ImageView img;

    @FXML
    private Label info;

    @FXML
    private Button backToMenu;
    @FXML
    private Button saveGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void playAgainAction(ActionEvent actionEvent) {

        if (!computerFlag) {
            playerTurn = rand.nextBoolean();
        } else {
            playerTurn = true;
        }

        System.out.println("computerFlag : " + computerFlag);
        System.out.println("playerTurn : " + playerTurn);
        System.out.println("playerOneName : " + playerOneName);

        //newGameBoard.view.hidePlayAgainButton();
        GameBoardController.view.resetVeiw();
        GameBoardController.model.resetGameControl();
        GameBoardController.view.setTurnMessage(playerOneName, playerTwoName, playerTurn);

        GameBoardController.gameStatus = GameBoardController.STATUS_PLAYING;
        GameBoardController.playerTurn = playerTurn;

        Stage window = (Stage) saveGame.getScene().getWindow();
        window.close();
    }

    @FXML
    public void playAgainHoverEnter(MouseEvent mouseEvent) {
        componentHover.ButtonHoverEnter(playAgain);
    }

    @FXML
    public void playAgainHoverExit(MouseEvent mouseEvent) {
        componentHover.ButtonHoverExit(playAgain);
    }

    @FXML
    public void saveGameHoverEnter(MouseEvent mouseEvent) {
        componentHover.ButtonHoverEnter(saveGame);
    }

    @FXML
    public void saveGameHoverExit(MouseEvent mouseEvent) {
        componentHover.ButtonHoverExit(saveGame);
    }

    @FXML
    public void saveGameAction(ActionEvent actionEvent) {
        System.out.println("Save Game");
        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/savePage.fxml"));
            root = loader.load();

            Stage window = (Stage) saveGame.getScene().getWindow();
            window.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            newScene.initOwner(window);
            newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
            newScene.setResizable(true);

            newScene.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void homePageHoverEnter(MouseEvent mouseEvent) {
        componentHover.ButtonHoverEnter(backToMenu);
    }

    @FXML
    public void homePageHoverExit(MouseEvent mouseEvent) {
        componentHover.ButtonHoverExit(backToMenu);
    }

    @FXML
    public void backToMenuAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/homePage.fxml"));
            Parent root = loader.load();

            Stage window = (Stage) saveGame.getScene().getWindow();
            window.close();
            Stage s = (Stage) window.getOwner();
            s.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            //newScene.initOwner(window);
            //newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
            newScene.setResizable(true);

            newScene.show();

            HomePageController homepageController = loader.getController();
            System.out.println("User.getPlayerName -> " + User.getPlayerName(GameBoardController.userID));
            System.out.println("User.getPlayerScore -> " + User.getPlayerScore(GameBoardController.userID));
            homepageController.setInformationFromLoginPage_(User.getPlayerName(GameBoardController.userID), Integer.toString(User.getPlayerScore(GameBoardController.userID)));
            homepageController.start();

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

    public void messageInfo(int status) {

        switch (status) {
            case 1:
                info.setText("You Win");
                img.setImage(new Image(getClass().getResource("../images/winner1.gif").toExternalForm()));
                break;

            case 2:
                info.setText("You Lose");
                img.setImage(new Image(getClass().getResource("../images/lose.gif").toExternalForm()));
                break;

            case 3:
                info.setText("Tie");
                img.setImage(new Image(getClass().getResource("").toExternalForm()));
                break;
        }

    }

}
