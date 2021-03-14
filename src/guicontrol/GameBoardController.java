/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicontrol;

import client.ClientHandler;
import database.Game;
import database.Move;
import database.User;
import help.gameResultHandel;
import gamehandler.ControlGameAction;
import gamehandler.ControlView;

import gamehandler.DatabaseHandler;
import gamehandler.TicTacNode;
import static guicontrol.LoginController.retval;
import help.ComponentHover;
import help.SceneNav;
import help.sound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

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
import serverimplementation.NewSession;
import serverimplementation.ServerImplementation;

/**
 * FXML Controller class
 *
 * @author Al-Qirawan
 */
public class GameBoardController implements Initializable, Runnable {

    int location_1 = -1;
    int location_2 = -1;
    int location_3 = -1;

    public static int idClient = -1;
    public static boolean flagOfIdClient = true;
    /**
     * ****************
     */
    boolean replayAgainFlag = false;
    public static boolean gameHistoryFlag = false;
    public static int gameId;
    ArrayList<Move> retrivedMoves;
    public static ArrayList<Move> moves = new ArrayList<>();
    //int userId = DatabaseHandler.id;
    public static final int STATUS_PLAYING = 1;
    public static final int STATUS_GAME_END = 0;
    sound newSound = new sound();
    Socket socket;
    public static int userID = -1;
//public static DataInputStream fromServer;
    //public static DataOutputStream toServer;
    public static DataInputStream fromPlayer2ToServer = null; //bst2bal PLAYER2 -->SERVER 
    public static DataOutputStream toPlayer2FromServer = null; //bb3t   SERVER --->PLAYER2
    private int SERVER_WON = 1;
    private int CLIENT_WON = 2;
    //private int DRAW =1;
    private int CONT = 1;
    private int Server_WON = 1;

    private int DRAW = 3;
    private int status;

    private int cont = 4;

    public boolean clientTurn = false;
    public static boolean onlineServerFlag = false;
    public static boolean onlineClientFlag = false;
    public static int playerOnline = -1;

    public static int clientPlayerOnline = -1;
    private boolean serverTurn = false;

    public static boolean hardFlag = false;
    int randomMove = 0;
    static TicTacNode node;
    public static ControlGameAction model;
    public static ControlView view;
    DatabaseHandler databaseHandler;

    int viewRetVal = 0;
    Random rand = new Random();

    private ComponentHover componentHover = new ComponentHover();
    private SceneNav newSceneNav = new SceneNav();
    private ControlGameAction check = new ControlGameAction();

    static boolean playerTurn = true;
    public static boolean isComputer = false;

    private int playerMark = ControlGameAction.X;
    private int anthorPlayerMark = ControlGameAction.O;
    public static int gameStatus = STATUS_PLAYING;

    private int result = 0;
    private int PlayerTwoScore = 0;

    private int PlayerOneScore = 0;
    private static String playerOneName;

    private static String playerTwoName;

    @FXML
    private Button playAgain;
    @FXML
    private Button about;
    @FXML
    private Button backToMenu;
    @FXML
    private Button replayGame;

    @FXML
    private Button exitBtn;

    @FXML
    private Label secondPlayer; //remove
    @FXML
    private Label secondPlayerLetter;//remove
    @FXML
    private Label userName;
    @FXML
    public static Label test;//remove
    @FXML
    private Label userScore;//remove
    @FXML
    private Label secondPlayerScore;//remove
    @FXML
    private Label gameInfo;
    @FXML
    private Label Label00;
    @FXML
    private Label Label01;
    @FXML
    private Label Label02;
    @FXML
    private Label Label10;
    @FXML
    private Label Label11;
    @FXML
    private Label Label12;
    @FXML
    private Label Label20;
    @FXML
    private Label Label21;
    @FXML
    private Label Label22;

    private Label[][] label = new Label[3][3];
    private Label[] Info;
    Thread t = new Thread(this);

    public static gameResultHandel newGameResultHandel = new gameResultHandel();
    ServerImplementation newServerImplementation;
    ClientHandler newClientHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Info = new Label[4];

        Info[0] = secondPlayer;
        Info[1] = userName;
        Info[2] = userScore;
        Info[3] = gameInfo;

        label[0][0] = Label00;
        label[0][1] = Label01;
        label[0][2] = Label02;
        label[1][0] = Label10;
        label[1][1] = Label11;
        label[1][2] = Label12;
        label[2][0] = Label20;
        label[2][1] = Label21;
        label[2][2] = Label22;

        model = new ControlGameAction();
        view = new ControlView(label, replayGame, playAgain, Info);
        node = new TicTacNode(model.getGameTrack());
        databaseHandler = new DatabaseHandler();

    }

    public void onlineHandler() {
        System.out.println(Thread.currentThread());

        newServerImplementation = new ServerImplementation(this);

    }

    public void joinHandler() {
        System.out.println("guicontrol.GameBoardController.joinHandler()");
        System.out.println(Thread.currentThread());

        newClientHandler = new ClientHandler(this);

        newClientHandler.connectToServer();
        //view.messageToInfo(User.getPlayerName(GameBoardController.userID) + " Turn");
    }

    public void start() {

        if (!isComputer) {
            playerTurn = rand.nextBoolean();
        } else {
            playerTurn = true;
        }

        PlayerOneScore = 0;

        gameStatus = STATUS_PLAYING;
        view.resetVeiw();
        model.resetGameControl();
        view.hideReplayAgainButton();
//        if (onlineServerFlag) {
//            System.out.println("guicontrol.GameBoardController.start()");
//            view.messageToInfo("Waiting For Player 2");
//        }
//        if (!isComputer) {
//            playerTurn = rand.nextBoolean();
//        } else {
//            playerTurn = true;
//        }
//
//        PlayerOneScore = 0;
//        // PlayerTwoScore = 0;
//
//        if (!onlineServerFlag) {
//            view.initiVeiw(databaseHandler.getName(), playerTwoName, playerTurn);
//            System.out.println("viewArray: " + view.viewArray[0][0].getText());
//            gameStatus = STATUS_PLAYING;
//            model.resetGameControl();
//            newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(databaseHandler.getScore()));
//        } else {
//            view.setUserName(playerOneName);
//            model.resetGameControl();
//        }

    }

    public void setGameId(int id) {
        gameHistoryFlag = true;
        this.gameId = id;
        System.out.println("gameId -> " + gameId);
    }

    @Override
    public void run() {

        //if (!replayAgainFlag) {
        retrivedMoves.forEach(move -> {

            int player = "x".equals(move.getSymbolxo()) ? 1 : 0;

            Platform.runLater(new Runnable() {
                public void run() {
                    view.updateVeiw((move.getLocation() / 3), (move.getLocation() % 3), player);
                    model.updateGameTrack((move.getLocation() / 3), (move.getLocation() % 3), player);
                    checkGameHistoryMode(player);
                }

            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        view.showReplayAgainButton();
        //}else{
//            try {
//                t.wait(2000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }

    public void onlineServerMode() {
        gameHistoryFlag = false;
        isComputer = false;
        System.out.println("guicontrol.GameBoardController.start()");
        view.messageToInfo("Waiting For Player 2");
        view.setUserName(databaseHandler.getName());
        moves.clear();
        //newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(databaseHandler.getScore()));
    }

    public void onlineClientMode() {
        gameHistoryFlag = false;
        isComputer = false;
        view.setUser2Name(databaseHandler.getName());
        //newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(databaseHandler.getScore()));
    }

    public void computerMode() {
        onlineServerFlag = false;
        onlineClientFlag = false;
        gameHistoryFlag = false;
        moves.clear();
        view.initiVeiw(databaseHandler.getName(), playerTwoName, playerTurn);
        //newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(databaseHandler.getScore()));
    }

    public void offlineMode() {
        onlineServerFlag = false;
        onlineClientFlag = false;
        gameHistoryFlag = false;
        isComputer = false;
        moves.clear();
        view.initiVeiw(databaseHandler.getName(), playerTwoName, playerTurn);
        //newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(databaseHandler.getScore()));
    }

    public void gameHistoryMode() {

        int row = -1;
        int coulum = -1;
        Game game1 = new Game();
        game1.getGameById(this.gameId);

        view.messageToInfo("Show History");
        view.setUserName(User.getPlayerName(game1.getPlayer1Id()));
        view.setUser2Name(User.getPlayerName(game1.getPlayer2Id()));
        retrivedMoves = Move.movesForGame(this.gameId);
        System.out.println(retrivedMoves.size());
        // if (!replayAgainFlag) {
        t.start();
        //}else{
        //    t.notifyAll();
        // }

    }

    public void changeComuterFlag() {

        isComputer = true;
    }

    public void setTurnFlag(boolean flag) {

        this.playerTurn = flag;
    }

    public void setHardFlag() {

        hardFlag = true;
    }

    public void setUserID(int id) {

        this.userID = id;
    }

    public void setOlineFlag() {

        onlineServerFlag = true;
    }

    public void setOlineClientFlag() {

        onlineClientFlag = true;
    }

    public void setPlayerOlineFlag() {

        playerOnline = 1;

        try {
            fromPlayer2ToServer = new DataInputStream(NewSession.secondPlayer.getInputStream()); //bst2bal 

            System.out.println("fromPlayer2ToServer : " + fromPlayer2ToServer);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        try {
            toPlayer2FromServer = new DataOutputStream(NewSession.secondPlayer.getOutputStream());//bb3t 

            System.out.println("toPlayer2FromServer : " + toPlayer2FromServer);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

        }

    }

    public void setClientPlayerOlineFlag() {

        System.out.println("guicontrol.GameBoardController.setClientPlayerOlineFlag()");
        clientPlayerOnline = 0;

        //socket = s;
//        try {
//            fromServer = new DataInputStream(ClientHandler.socket.getInputStream());
//        } catch (IOException ex) {
//            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//        try {
//            toServer = new DataOutputStream(ClientHandler.socket.getOutputStream());
//
//        } catch (IOException ex) {
//            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//
//        System.out.println("clientPlayerOnline = " + clientPlayerOnline);
//        System.out.println("socket = " + ClientHandler.socket);
//        System.out.println("fromServer = " + fromServer);
//        System.out.println("toServer = " + toServer);
    }

    public void resetHardFlag() {

        hardFlag = false;
    }

    public void getUserInformation(String user) {
        playerOneName = user;
    }

    public void getUserInformation(String user, String score) {

        playerOneName = user;
        PlayerOneScore = Integer.parseInt(score);
    }

    public void getSecondPlayerInformation(String user) {
        playerTwoName = user;

    }

    @FXML
    synchronized public void onEvent(MouseEvent event) {

        if (!gameHistoryFlag) {
            if (onlineServerFlag) {
                onlineServerMode(event);
            } else if (onlineClientFlag) {
                onlineClientMode(event);
            } else {
                offlineMode(event);
            }
        }

    }

    public void onlineServerMode(MouseEvent event) {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if ((playerOnline == 1) && (gameStatus == STATUS_PLAYING) && (event.getSource() == label[row][column]) && (view.getCell(row, column) == "")) {
                    int location = ((row * 3) + (column % 3));
                    System.out.println("Row : " + row + "Colunm : " + column);
                    System.out.println(label[row][column]);
                    int player = playerOnline == 1 ? playerMark : anthorPlayerMark;
                    System.out.println("Player: " + player);

                    if (playerOnline == 1) { // turn mnen  server turn EFILLLLLLLLLA
                        newSound.playMusic("../sound/game-alert-sweep.wav");
                        view.updateVeiw(row, column, player); //player1
                        model.updateGameTrack(row, column, player);
                        moves.add(new Move("x", location, 1));
                        moves.forEach(move -> System.out.println("X :"+move.getLocation()));
                    }
                    playerOnline = 0;
                    checkMulti(player, row, column); // x o menen

                }

            }
        }
    }

    public void onlineClientMode(MouseEvent event) {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if ((clientPlayerOnline == 1) && (gameStatus == STATUS_PLAYING) && (event.getSource() == label[row][column]) && (view.getCell(row, column) == "")) {
                    int location = ((row * 3) + (column % 3));
                    System.out.println("Row : " + row + "Colunm : " + column);
                    System.out.println(label[row][column]);
                    System.out.println("Row : " + row + "Colunm : " + column);
                    System.out.println(label[row][column]);
                    int player = clientPlayerOnline == 1 ? anthorPlayerMark : playerMark; // O
                    System.out.println("Player: " + player);
                    newSound.playMusic("../sound/game-alert-sweep.wav");
                    view.updateVeiw(row, column, player); //player1
                    moves.add(new Move("o", location, 1));
                    moves.forEach(move -> System.out.println("O :"+move.getLocation()));
                    try {
                        ClientHandler.toServer.writeInt(row);
                        System.out.println("row: " + row);
                    } catch (IOException ex) {
                        Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        ClientHandler.toServer.writeInt(column);
                        System.out.println("column: " + column);

                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clientPlayerOnline = 0;

                }

            }
        }
    }

    public void resultMessage(int status) {
        newGameResultHandel.resultMessage(this.userName, status);
    }

    public void offlineMode(MouseEvent event) {
        System.out.println(label[0][0]);
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if ((gameStatus == STATUS_PLAYING) && (event.getSource() == label[row][column]) && (view.getCell(row, column) == "")) {
                    int location = ((row * 3) + (column % 3));
                    System.out.println("Row : " + row + "Colunm : " + column);
                    System.out.println(label[row][column]);
                    System.out.println("location : " + location);
                    int player = playerTurn ? playerMark : anthorPlayerMark;
                    System.out.println("Player: " + player);
                    if (playerTurn) {
                        newSound.playMusic("../sound/game-alert-sweep.wav");
                        viewRetVal = view.updateVeiw(row, column, player);

                        moves.add(new Move("x", location, 1));

                        model.updateGameTrack(row, column, player);
                    } else if (!isComputer && !playerTurn) {
                        newSound.playMusic("../sound/ball-tap.wav");
                        viewRetVal = view.updateVeiw(row, column, player);
                        model.updateGameTrack(row, column, player);
                        moves.add(new Move("o", location, 1));
                    }

                    if (viewRetVal == 1) {
                        check(player);
                        viewRetVal = 0;
                    }
                    playerTurn = !playerTurn;
                    player = playerTurn ? playerMark : anthorPlayerMark;

                    if (isComputer && gameStatus == STATUS_PLAYING) {
                        System.out.println("hi  computer");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });

                        randomMove = hardFlag ? model.getCompMove() : model.random();
                        System.out.println("randomMove -> " + randomMove);
                        System.out.println("hardFlag -> " + hardFlag);
                        viewRetVal = view.updateVeiw((randomMove / 3), (randomMove % 3), anthorPlayerMark);
                        model.updateGameTrack((randomMove / 3), (randomMove % 3), anthorPlayerMark);
                        moves.add(new Move("o", randomMove, 1));
                        newSound.playMusic("../sound/ball-tap.wav");
                        if (viewRetVal == 1) {
                            check(player);
                            viewRetVal = 0;
                        }

                        playerTurn = !playerTurn;
                    }

                }

                moves.forEach(move -> System.out.println("move -> " + move.getSymbolxo() + "move -> " + move.getLocation()));

            }

        }
    }

    public void check(int player) {
        System.out.println("guicontrol.GameBoardController.check()");
        if (model.checkWin(player)) {
            System.out.println("Player " + player + " wins");
            PlayerOneScore = (player == playerMark ? (PlayerOneScore) : PlayerOneScore);
            PlayerTwoScore = (player == anthorPlayerMark ? (++PlayerTwoScore) : PlayerTwoScore);

            System.out.println("Player 1 Score: " + PlayerOneScore);

            System.out.println("check -> userId : " + databaseHandler.getId());
            //view.updateScore(PlayerOneScore, PlayerTwoScore, player);

            view.winVeiw();
            //view.showPlayAgainButton();

            gameStatus = STATUS_GAME_END;
            if (player == 1) {
                newSound.playMusic("../sound/win.mp3");
                newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(PlayerOneScore));
                databaseHandler.updateScore(userID);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                            newGameResultHandel.resultMessage(userName, 1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } else {
                newSound.playMusic("../sound/lose.wav");
                newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(PlayerOneScore));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                            newGameResultHandel.resultMessage(userName, 2);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }

        } else if (!model.checkDraw()) {
            view.messageToInfo("Tie");
            //view.showPlayAgainButton();

            newGameResultHandel.playerInfo(isComputer, playerOneName, playerTwoName, Integer.toString(PlayerOneScore));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        newGameResultHandel.resultMessage(userName, 3);
                        newSound.playMusic("../sound/draw.wav");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            gameStatus = STATUS_GAME_END;
        }

    }

    public void checkGameHistoryMode(int player) {

        if (model.checkWin(player)) {

            view.winVeiw();

        } else if (!model.checkDraw()) {

        }

    }

    @FXML
    public void playAgainAction(ActionEvent actionEvent) {
        newSound.playMusic("../sound/click-box-check.wav");
        if (!isComputer) {
            playerTurn = rand.nextBoolean();
        } else {
            playerTurn = true;
        }

        view.hidePlayAgainButton();
        view.resetVeiw();
        model.resetGameControl();
        view.setTurnMessage(playerOneName, playerTwoName, playerTurn);

        gameStatus = STATUS_PLAYING;
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
    public void replayGameHoverEnter(MouseEvent mouseEvent) {
        componentHover.ButtonHoverEnter(replayGame);
    }

    @FXML
    public void replayGameHoverExit(MouseEvent mouseEvent) {
        componentHover.ButtonHoverExit(replayGame);
    }

    @FXML
    public void replayGameAction(ActionEvent actionEvent) {
        // newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/homePage.fxml"));
            Parent root = loader.load();

            newSceneNav.naveTo(root, userName);

            HomePageController homepageController = loader.getController();
            homepageController.setInformationFromLoginPage_(databaseHandler.getName(), Integer.toString(databaseHandler.getScore()));
            homepageController.setUserId(retval);
            homepageController.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void backToMenuAction(ActionEvent actionEvent) {
        //newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/backToHomeMessage.fxml"));
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

            BackToHomeMessageController newMessage = loader.getController();
            newMessage.setUserInfo(Integer.toString(PlayerOneScore), userName.getText());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void aboutAction(ActionEvent actionEvent) {
        //newSound.playMusic("../sound/click-box-check.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guidesgin/AboutPage.fxml"));
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

    @FXML
    public void exitAction(ActionEvent actionEvent) {
        //newSound.playMusic("../sound/click-box-check.wav");
        Stage window = (Stage) userName.getScene().getWindow();
        window.close();
    }

//    public static void delay(int t) {
//        try {
//            Thread.sleep(t);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//    }
    public void checkMulti(int player, int row, int column) { //win 
        if (model.checkWin(player)) {

            location_1 = ((ControlView.winnerArray_row[0] * 3) +  (ControlView.winnerArray_column[0] % 3));
            location_2 = ((ControlView.winnerArray_row[1] * 3) +  (ControlView.winnerArray_column[1] % 3));
            location_3 = ((ControlView.winnerArray_row[2] * 3) +  (ControlView.winnerArray_column[2] % 3));
            
            System.out.println("idClient from Multi  = " + idClient);
            System.out.println("id Server from Multi  = " + userID);
            System.out.println("Player " + player + " wins");
            PlayerOneScore = (player == playerMark ? (++PlayerOneScore) : PlayerOneScore);
            PlayerTwoScore = (player == anthorPlayerMark ? (++PlayerTwoScore) : PlayerTwoScore);
            if (player == ControlGameAction.X) { //server 

                try {
                    newSound.playMusic("../sound/win.mp3");
                    newGameResultHandel.resultMessage(userName, 1);
                    databaseHandler.updateScore(userID);

                    
                    toPlayer2FromServer.writeInt(Server_WON); //handel fe client 
                    toPlayer2FromServer.writeInt(location_1);
                    toPlayer2FromServer.writeInt(location_2);
                    toPlayer2FromServer.writeInt(location_3);

                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                sendMove(toPlayer2FromServer, row, column);//bb3t 

            }
            if (player == ControlGameAction.O) {

                try {
                    newSound.playMusic("../sound/lose.wav");
                    System.out.println("CLIENT_WON");
                    databaseHandler.updateClientScore(idClient, (User.getPlayerScore(idClient) + 1));
                    newGameResultHandel.resultMessage(userName, 2);
                    toPlayer2FromServer.writeInt(CLIENT_WON); //handel fe client 

                    toPlayer2FromServer.writeInt(location_1);
                    toPlayer2FromServer.writeInt(location_2);
                    toPlayer2FromServer.writeInt(location_3);
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }

            }
            view.winVeiw();
            view.showPlayAgainButton();
            databaseHandler.setScore(PlayerOneScore);
            gameStatus = STATUS_GAME_END;

        } else if (!model.checkDraw()) { // draw 

            if (player == ControlGameAction.X)// server 
            {  // int variable draw
                view.messageToInfo("Draw");
                try {
                    newGameResultHandel.resultMessage(userName, 3);
                    toPlayer2FromServer.writeInt(DRAW);//bb3t //handel 
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                sendMove(toPlayer2FromServer, row, column); //handel 
            }
            if (player == ControlGameAction.O) {
                try {
                    toPlayer2FromServer.writeInt(DRAW);//bb3t //handel 
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            view.showPlayAgainButton();
            gameStatus = STATUS_GAME_END;
        } else {
            if (player == ControlGameAction.X) {
                try {
                    toPlayer2FromServer.writeInt(cont);//bb3t //handel 
                    System.out.println("cont: " + cont);
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                sendMove(toPlayer2FromServer, row, column);
                /*try {
                    toPlayer2FromServer.writeBoolean(!clientTurn); //??
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }*/
            }
            /*if (player == ControlGameAction.O) {
                try {
                   // toPlayer2FromServer.writeInt(cont);
                     
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }*/
        }

    }

    private void sendMove(DataOutputStream out, int row, int column) {

        try {

            out.writeInt(row);
            System.out.println("sendMove -> row : " + row);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            out.writeInt(column);
            System.out.println("sendMove -> column : " + column);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

//    @Override
//    public void run() {
//        int column = -1;
//        int row = -1;
//        while (true) {
//
//            if ((playerOnline == 0)) {
//                System.out.println("guicontrol.GameBoardController.run()");
//
//                try {
//                    System.out.println("row : " + row);
//                    row = fromPlayer2ToServer.readInt();
//                    System.out.println("row : " + row);
//                } catch (IOException ex) {
//                    Logger.getLogger(NewSession.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                }
//                try {
//                    System.out.println("column : " + column);
//                    column = fromPlayer2ToServer.readInt();
//                    System.out.println("column : " + column);
//                } catch (IOException ex) {
//                    Logger.getLogger(NewSession.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                }
//                int player = playerOnline == 1 ? playerMark : anthorPlayerMark; //WHYYYYYYYYYYYYYYYYY o
//                view.updateVeiw(row, column, player); //draw in server side 
//                model.updateGameTrack(row, column, player);
//                checkMulti(player, row, column); //o 
//                playerOnline = 1;
//            }
//            try {
//                t.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
}
