/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverimplementation;

import guicontrol.GameBoardController;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author LENOVO
 */
public class ServerImplementation implements Runnable {

    ServerSocket serverSocket = null;
    Socket secondPlayer = null;
    GameBoardController gameBoard;
    Thread t = new Thread(this);

    //click host 
    public ServerImplementation(GameBoardController gameBoard ) {
        this.gameBoard = gameBoard;
        try {
            System.out.println(Thread.currentThread());

            serverSocket = new ServerSocket(5001);
            t.start();
            //System.out.println(new Date() + "Waiting for Player 2");//EGILA 
        } catch (IOException ex) {
            Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread());

            System.out.println("ServerImplementation");
            secondPlayer = serverSocket.accept();
            NewSession task = new NewSession(secondPlayer, gameBoard);
//            newGameBoardController.setPlayerOlineFlag();
            System.out.println("secondPlayer :" + secondPlayer);
        } catch (IOException ex) {
            Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

}
