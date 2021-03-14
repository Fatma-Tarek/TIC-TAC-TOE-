/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.stage.Modality;

import static javafx.stage.Modality.WINDOW_MODAL;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class HomePageController implements Initializable {

    Stage newScene = new Stage();
    static int newScore = 0;
    public static int useId = 0;
    String name;
    Parent root;
    FXMLLoader loader;
    ComponentHover componentHover = new ComponentHover();
    SelectLevelMessageController newSelectLevel = new SelectLevelMessageController();
    SceneNav newSceneNav = new SceneNav();
    sound newSound = new sound();
    DatabaseHandler newDatabaseHandler = new DatabaseHandler();

    @FXML
    private Label userName;
    @FXML
    private Button singlePlayer;

    @FXML
    private Button offlineMultiplePlayers;

    @FXML
    private Button onlineMultiplePlayers;

    @FXML

    //remove
    private Button playWithFriends;
    @FXML
    private Button about;
    @FXML
    private Button previousGames;
    @FXML
    private Label userScore;

    //remove
    @FXML
    private Label PlayOnline;

    //remove
    @FXML
    private Label PlayOffline;

    @FXML
    private Button Logout;

    @FXML
    private Button exitBtn;

    @FXML
    private Label hard;
    @FXML
    private Label easy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void start() {
        userName.setText(newDatabaseHandler.getPlayerName(useId));
        userScore.setText(Integer.toString(newDatabaseHandler.getPlayerScore(useId)));
        System.out.println("Start -> PlayerName" + newDatabaseHandler.getPlayerName(useId));
        System.out.println("Start -> PlayerScore" + newDatabaseHandler.getPlayerScore(useId));

    }

    public void setInformationFromLoginPage_(String username) {

        //userName = username;
        userName.setText(username);

    }

    public void setUserId(int useId) {

        this.useId = useId;
    }

    public void setInformationFromLoginPage_(String username, String score) {

        userName.setText(username);
        userScore.setText(score);

    }

    public void setInformationFromLoginPage(String username, String score) {

        newScore = newScore + Integer.parseInt(score);
        userName.setText(username);
        userScore.setText(Integer.toString(newScore));

    }

    public String getInformationFromLoginPage() {

        //userName = username;
        return userName.getText();
    }

    public void singlePlayerAction(ActionEvent event) {

        //newSound.playMusic("../sound/click-box-check.wav");
        // pop-up to choose [Easy/Hard] 
        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/selectLevelMessage.fxml"));
            root = loader.load();

            Stage window = (Stage) userName.getScene().getWindow();
            //window.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            newScene.initOwner(window);
            newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
            newScene.setResizable(true);

            newScene.show();

            SelectLevelMessageController newSelectLevel = loader.getController();
            newSelectLevel.setUserName(userName.getText());
            newSelectLevel.setUserID(useId);
            //newSceneNav.naveTo(root, userName);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

//    public void playWithComputerEasyAction(MouseEvent event) {
//
//        try {
//            loader = new FXMLLoader(getClass().getResource("PlayWithComputerPage.fxml"));
//            root = loader.load();
//
//            newSceneNav.naveTo(root, userName);
//
//            PlayWithComputerPageController playWithComputerPageController = loader.getController();
//            playWithComputerPageController.getUserInformation(userName.getText());
//            playWithComputerPageController.getSecondPlayerInformation("Computer");
//            playWithComputerPageController.changeComuterFlag();
//            playWithComputerPageController.resetHardFlag();
//
//            playWithComputerPageController.start();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    public void playWithComputerHardAction(MouseEvent event) {
//
//        try {
//            loader = new FXMLLoader(getClass().getResource("PlayWithComputerPage.fxml"));
//            root = loader.load();
//
//            newSceneNav.naveTo(root, userName);
//            PlayWithComputerPageController playWithComputerPageController = loader.getController();
//            playWithComputerPageController.getUserInformation(userName.getText());
//            playWithComputerPageController.getSecondPlayerInformation("Computer");
//            playWithComputerPageController.changeComuterFlag();
//            playWithComputerPageController.setHardFlag();
//
//            playWithComputerPageController.start();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }
    public void offlineMultiplePlayersAction(ActionEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        System.out.println("offlineMultiplePlayersAction");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/getSecondPlayerName.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) userName.getScene().getWindow();
            //window.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            newScene.initOwner(window);
            newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
            newScene.setResizable(true);

            newScene.show();
            GetSecondPlayerNameController secondPlayer = loader.getController();
            secondPlayer.getUserInformation(userName.getText(), userScore.getText());
            secondPlayer.setUserID(useId);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void onlineMultiplePlayersAction(ActionEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/onlinePage.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) userName.getScene().getWindow();
            //window.close();

            Stage newScene = new Stage();
            newScene.setScene(new Scene(root));

            newScene.initOwner(window);
            newScene.initModality(WINDOW_MODAL);
            newScene.setTitle("Tic Tac Toe Aplication");
            newScene.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
            newScene.setResizable(true);

            newScene.show();
            OnlinePageController newOnlinePageController = loader.getController();
            newOnlinePageController.setUserName(userName.getText());
            newOnlinePageController.setUserId(useId);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void previousGamesAction(ActionEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/previousGamesPage.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) userName.getScene().getWindow();
            Stage stage = new Stage();
            stage.initOwner(window);
            stage.initModality(Modality.WINDOW_MODAL);
            newSceneNav.naveTo(root, userName);
//            stage.setScene(new Scene(root));
//
//            stage.setTitle("Tic Tac Toe Aplication");
//
//            stage.show();

            PreviousGamesPageController test = loader.getController();
            test.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void aboutGameAction(ActionEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/aboutPage.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) userName.getScene().getWindow();
            Stage stage = new Stage();
            stage.initOwner(window);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Tic Tac Toe Aplication");
            stage.getIcons().add(new Image("/guidesgin/../images/logo2.png"));
            stage.setResizable(true);

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void logoutAction(ActionEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/Login.fxml"));
            Parent root = loader.load();
            newSceneNav.naveTo(root, userName);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void exitAction(ActionEvent event) {
        //newSound.playMusic("../sound/click-box-check.wav");
        Stage window = (Stage) userName.getScene().getWindow();
        window.close();
    }

    public void LogoutHoverEnter(MouseEvent event) {

        //componentHover.TextHoverEnter(Logout);
    }

    public void LogoutHoverExit(MouseEvent event) {

        //componentHover.TextHoverExit(Logout);
    }

    public void singlePlayerHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(singlePlayer);

    }

    public void singlePlayerHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(singlePlayer);
    }

    public void onlineMultiplePlayersHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(onlineMultiplePlayers);

    }

    public void onlineMultiplePlayersHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(onlineMultiplePlayers);

    }

    public void offlineMultiplePlayersHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(offlineMultiplePlayers);

    }

    public void offlineMultiplePlayersHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(offlineMultiplePlayers);

    }

    public void PlayHardHoverEnter(MouseEvent event) {

        componentHover.TextHoverEnter(hard);

    }

    public void PlayHardHoverExit(MouseEvent event) {

        componentHover.TextHoverExit(hard);

    }

    public void PlayEasyHoverEnter(MouseEvent event) {

        componentHover.TextHoverEnter(easy);

    }

    public void PlayEasyHoverExit(MouseEvent event) {

        componentHover.TextHoverExit(easy);

    }

    public void PlayWithFrendHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(playWithFriends);

    }

    public void PlayWithFrendHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(playWithFriends);
    }

    public void AboutHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(about);

    }

    public void AboutHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(about);
    }

    public void PreviousGamesHoverEnter(MouseEvent event) {

        componentHover.ButtonHoverEnter(previousGames);

    }

    public void PreviousGamesHoverExit(MouseEvent event) {

        componentHover.ButtonHoverExit(previousGames);
    }

    public void setVisible() {
        PlayOnline.setVisible(false);
        PlayOffline.setVisible(false);
        hard.setVisible(false);
        easy.setVisible(false);
    }
}
