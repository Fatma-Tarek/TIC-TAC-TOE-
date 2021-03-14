/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverimplementation;

import database.Move;
import database.User;
import gamehandler.ControlGameAction;
import gamehandler.ControlView;
import gamehandler.DatabaseHandler;
import guicontrol.GameBoardController;
import static guicontrol.GameBoardController.moves;
import static guicontrol.GameBoardController.view;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author LENOVO
 */
//////////////////Method////////////////////
public class NewSession implements Runnable {

    private int playerMark = ControlGameAction.X;
    private int anthorPlayerMark = ControlGameAction.O;
    Thread t = new Thread(this);
    public static boolean multiPlayer = false;
    public static Socket secondPlayer;
    GameBoardController gameBoard;
    int column = -1;
    int row = -1;

    public NewSession(Socket secondPlayer, GameBoardController gameBoard) {////// MODEL /////
        this.secondPlayer = secondPlayer;
        this.gameBoard = gameBoard;
        gameBoard.setPlayerOlineFlag();

        if (GameBoardController.flagOfIdClient) {
            try {
                GameBoardController.idClient = GameBoardController.fromPlayer2ToServer.readInt();
                System.out.println("Id Client recieved=" + GameBoardController.idClient);

                int idServer = DatabaseHandler.getId();
                GameBoardController.toPlayer2FromServer.writeInt(idServer);
                String clientName = User.getPlayerName(GameBoardController.idClient);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GameBoardController.view.setUser2Name(clientName);
                        GameBoardController.view.messageToInfo(User.getPlayerName(idServer) + " Turn");
                    }
                });
            } catch (IOException ex) {
                Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            GameBoardController.flagOfIdClient = false;
        }

        multiPlayer = true;
        t.start();
    }

    @Override
    public void run() {

        while (true) {

            if ((GameBoardController.playerOnline == 0)) {
                System.out.println("guicontrol.GameBoardController.run()");
               
                try {
                    System.out.println("row : " + row);
                    System.out.println("fromPlayer2ToServer : " + GameBoardController.fromPlayer2ToServer);
                    row = GameBoardController.fromPlayer2ToServer.readInt();
                    System.out.println("row : " + row);
                } catch (IOException ex) {
                    Logger.getLogger(NewSession.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                try {
                    System.out.println("column : " + column);
                    column = GameBoardController.fromPlayer2ToServer.readInt();
                    System.out.println("column : " + column);
                    int location = ((row * 3) + (column % 3));
                    moves.add(new Move("o", location, 1));
                    moves.forEach(move -> System.out.println("O :" + move.getLocation()));
                } catch (IOException ex) {
                    Logger.getLogger(NewSession.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                int player = GameBoardController.playerOnline == 1 ? playerMark : anthorPlayerMark; //WHYYYYYYYYYYYYYYYYY o
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GameBoardController.view.updateVeiw(row, column, player); //draw in server side 
                        GameBoardController.model.updateGameTrack(row, column, player);
                        gameBoard.checkMulti(player, row, column); //o 
                        GameBoardController.playerOnline = 1;
                    }
                });

            }
            try {
                t.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
