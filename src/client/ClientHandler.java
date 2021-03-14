/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import database.Move;
import database.User;
import gamehandler.ControlGameAction;
import gamehandler.DatabaseHandler;
import guicontrol.GameBoardController;
import static guicontrol.GameBoardController.moves;
import help.gameResultHandel;
import help.sound;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ClientHandler implements Runnable {

    public static int location[] = new int[3];
    sound newSound = new sound();
    public static int id = 0;
    int idServer;
    private boolean sendId = true;
    /**
     * ************
     */
    public static Socket socket;

    private int playerMark = ControlGameAction.X;
    private int anthorPlayerMark = ControlGameAction.O;
    int row = -1;
    int column = -1;
    private int SERVER_WON = 1;
    private int CLIENT_WON = 2;
    //private int DRAW =1;
    private int CONT = 4;
    private int Server_WON = 1;

    private int DRAW = 3;
    private int status;

    private int cont = 4;

    public static DataInputStream fromServer;
    public static DataOutputStream toServer;
    private boolean continueToPlay = true;
    private boolean waiting = true;
    private boolean isStandAlone = false;
    Thread thread = new Thread(this);
    GameBoardController gameBoard;

    public ClientHandler(GameBoardController gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void connectToServer() {
        /**
         * ***********************************
         */
        try {
            //if it is standalone connect to the localhost
//            if (isStandAlone) {
//                socket = new Socket("localhost", 5001);
//            } else {
//                socket = new Socket(InetAddress.getLocalHost(), 5001);
//                System.out.println(InetAddress.getLocalHost());
//                System.out.println("socket" + socket);
//            }
            socket = new Socket("127.0.0.1", 5001);
            System.out.println("socket" + socket);
        } catch (IOException ex) {
            if (socket.isClosed()) {
                System.out.println("Server is Close");
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Server Maybe sleeping");
                alert.setHeaderText("QUIET!!");
                alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                alert.showAndWait();
                System.exit(0);
            } else if (!(socket.isConnected())) {

                System.out.println("Can't Reach ");
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Server Maybe sleeping");
                alert.setHeaderText("QUIET!!");
                alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                alert.showAndWait();
                System.exit(0);

            } else {

                ex.printStackTrace();
            }
        }

        try {
            fromServer = new DataInputStream(socket.getInputStream());
            System.out.println("fromServer : " + fromServer);
        } catch (IOException ex) {
            if (socket.isClosed()) {
                System.out.println("Server is Close");
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Server Maybe sleeping");
                alert.setHeaderText("QUIET!!");
                alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                alert.showAndWait();
                System.exit(0);
            } else if (!(socket.isConnected())) {

                System.out.println("Can't Reach ");
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Server Maybe sleeping");
                alert.setHeaderText("QUIET!!");
                alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                alert.showAndWait();
                System.exit(0);

            } else {

                ex.printStackTrace();
            }
        }
        try {
            toServer = new DataOutputStream(socket.getOutputStream());
            System.out.println("toServer : " + toServer);
        } catch (IOException ex) {
            if (socket.isClosed()) {
                System.out.println("Server is Close");
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Server Maybe sleeping");
                alert.setHeaderText("QUIET!!");
                alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                alert.showAndWait();
                System.exit(0);
            } else if (!(socket.isConnected())) {

                System.out.println("Can't Reach ");
                System.out.println(ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Server Maybe sleeping");
                alert.setHeaderText("QUIET!!");
                alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                alert.showAndWait();
                System.exit(0);

            } else {

                ex.printStackTrace();
            }
        }

        thread.start();
    }

    @Override
    public void run() {

        if (sendId) {
            id = DatabaseHandler.getId();
            System.out.println("Id =" + id);
            try {
                toServer.writeInt(id);

            } catch (IOException ex) {
                if (socket.isClosed()) {
                    System.out.println("Server is Close");
                    System.out.println(ex.getMessage());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Server Maybe sleeping");
                    alert.setHeaderText("QUIET!!");
                    alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                    alert.showAndWait();
                    System.exit(0);
                } else if (!(socket.isConnected())) {

                    System.out.println("Can't Reach ");
                    System.out.println(ex.getMessage());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Server Maybe sleeping");
                    alert.setHeaderText("QUIET!!");
                    alert.setContentText("The server MIGHT be down, Please connect later the application will now close");
                    alert.showAndWait();
                    System.exit(0);

                } else {

                    ex.printStackTrace();
                }
            }

            try {
                idServer = fromServer.readInt();
            } catch (IOException ex) {

                try {

                    fromServer.close();
                    toServer.close();
                    socket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            System.out.println("idServer =" + idServer);
            System.out.println(User.getPlayerName(idServer));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    GameBoardController.view.setUserName(User.getPlayerName(idServer));
                    GameBoardController.view.messageToInfo(User.getPlayerName(idServer) + " Turn");
                }
            });

            sendId = false;
        }

        //Platform.runLater(this);
        System.out.println("client.ClientHandler.run()");
        while (true) {

            if (GameBoardController.clientPlayerOnline == 0) {
                System.out.println("guicontrol.GameBoardController.run()");
//                try {
//                    //System.out.println("client.ClientHandler.run()");
//                    //clientTurn = fromServer.readInt();
//                } catch (IOException ex) {
//                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }

                int player = GameBoardController.clientPlayerOnline == 0 ? playerMark : anthorPlayerMark; // X
                try {
                    status = fromServer.readInt();
                    System.out.println("status: " + status);
                } catch (IOException ex) {

                    ex.printStackTrace();
                }
                if (status == SERVER_WON) {
                    try {
                        System.out.println("SERVER_WON");

                        location[0] = fromServer.readInt();
                        location[1] = fromServer.readInt();
                        location[2] = fromServer.readInt();
                        System.out.println("location[0] -> " + location[0]);
                        System.out.println("location[1] -> " + location[1]);
                        System.out.println("location[2] -> " + location[2]);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                GameBoardController.view.winVeiw();///EGILLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLA

                                GameBoardController.view.showPlayAgainButton();
                                gameBoard.resultMessage(2);
                                newSound.playMusic("../sound/lose.wav");

                            }
                        });

                        GameBoardController.gameStatus = GameBoardController.STATUS_GAME_END;/////
                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (status == DRAW) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GameBoardController.view.messageToInfo("Draw");
                            GameBoardController.view.showPlayAgainButton();
                            gameBoard.resultMessage(3);
                        }
                    });

                    GameBoardController.gameStatus = GameBoardController.STATUS_GAME_END;
                } else if (status == CLIENT_WON) {

                    try {
                        location[0] = fromServer.readInt();
                        location[1] = fromServer.readInt();
                        location[2] = fromServer.readInt();
                        System.out.println("location[0] -> " + location[0]);
                        System.out.println("location[1] -> " + location[1]);
                        System.out.println("location[2] -> " + location[2]);
                        System.out.println("CLIENT_WON");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                GameBoardController.view.winVeiw();
                                GameBoardController.view.winClientVeiw(location);
                                GameBoardController.view.showPlayAgainButton();
                                gameBoard.resultMessage(1);
                                newSound.playMusic("../sound/win.mp3");
                            }
                        });

                        GameBoardController.gameStatus = GameBoardController.STATUS_GAME_END;/////
                    } catch (IOException ex) {
                        Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (status == CONT) {
                    GameBoardController.clientPlayerOnline = 1;
                }

                try {

                    row = fromServer.readInt();
                    System.out.println("row: " + row);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {

                    column = fromServer.readInt();
                    int location = ((row * 3) + (column % 3));
                    System.out.println("column: " + column);
                    moves.add(new Move("x", location, 1));
                    moves.forEach(move -> System.out.println("O :" + move.getLocation()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        if (status == cont) {
                            GameBoardController.view.updateVeiw(row, column, player);
                        } else if (status == SERVER_WON) {
                            GameBoardController.view.updateVeiwWinner(row, column, player);
                            GameBoardController.view.winClientVeiw(location);
                        }
                    }
                });

            }
            try {
                thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

}
