/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import database.Game;
import database.User;
import gamehandler.DatabaseHandler;
import static guicontrol.SelectLevelMessageController.userID;
import help.SceneNav;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class PreviousGamesPageController implements Initializable {

    Parent root;
    FXMLLoader loader;
    SceneNav newSceneNav = new SceneNav();

    
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn gameName;
    @FXML
    private ListView listView;
    @FXML
    private TextField text;

    @FXML
    private Button show;

    static int id1;
    static String gameName1;
    static int[][] arr = new int[2][9];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setItems(int id, String gameName, int[] ar) {
        this.id1 = id;

        this.gameName1 = gameName;

        this.arr[id - 1] = ar;
    }

    public void start() {

        ArrayList<Game> games = Game.GamesForPlayer(DatabaseHandler.loginUser.getId());
        System.out.println(games.size());

        ObservableList<String> names = FXCollections.observableArrayList();
        ArrayList<String> t = new ArrayList<String>();

//         games.forEach(game -> System.out.println(
//                    game.getId() + "--" +                                   // get game id
//                    game.getName() + "--" +                                 // get game name
//                    User.getPlayerName(game.getPlayer1Id()) + "--" +        // get player name from user table using player id from game table
//                    User.getPlayerName(game.getPlayer2Id()) + "--" +        // get player name from user table using player id from game table
//                    game.getDateTime()                                      // get date and time
//            
//         )
//                 
//        );
        games.forEach(game -> {

            names.add(Integer.toString(game.getId()) + " : " + game.getName() + " : "
                    + User.getPlayerName(game.getPlayer1Id()) + " : " + User.getPlayerName(game.getPlayer2Id()) + " : " + game.getDateTime());
            listView.setItems(names);
        });

    }

    @FXML
    public void ShowAction(ActionEvent event) {

        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/homePage.fxml"));
            Parent root = loader.load();

            newSceneNav.naveTo(root, email);

            HomePageController homepageController = loader.getController();
            homepageController.setInformationFromLoginPage_(username, Integer.toString(userScore));
            homepageController.setUserId(retval);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
         */
    }

    public void onClick(MouseEvent event) {
        //System.out.println("clickSource" +  event.getTarget());
        String[] x = listView.getSelectionModel().getSelectedItems().get(0).toString().split(" ");

        System.out.println(x[0]);
        System.out.println(x[1]);
        System.out.println(x[2]);
        System.out.println(x[3]);

        GameBoardController newGameBoardController = new GameBoardController();

        try {
            loader = new FXMLLoader(getClass().getResource("/guidesgin/gameBoard.fxml"));
            root = loader.load();
            Stage window = (Stage) listView.getScene().getWindow();
//            Stage s = (Stage) window.getOwner();
//            s.close();
            newSceneNav.naveTo(root, listView);

            GameBoardController gameBoard = loader.getController();
           
            
            gameBoard.setGameId(Integer.parseInt(x[0]));
            gameBoard.start();
            gameBoard.gameHistoryMode();
            
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
