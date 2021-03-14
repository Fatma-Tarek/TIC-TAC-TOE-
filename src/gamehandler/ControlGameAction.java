/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamehandler;

import java.util.Date;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Al-Qirawan
 */
public class ControlGameAction {

    //private int [] gameTrack_X = new int[5];
    //private int [] gameTrack_O = new int[5];
    public static final int EMPTY = -1;
    public static final int O = 0;
    public static final int X = 1;
    public static int sequanceIndex = 0;
    int[] colunm = new int[3];
    int[] row = new int[3];

    ControlView view = new ControlView();
    int randomRowMove;
    int randomColunmMove;

    static int[] gameHistory = new int[9];
    static int[] gameSequnce = new int[9];
    //static Date []newDate         = new Date[9]; 

    private  int[][] gameTrack = new int[3][3];
    private Vector<Integer> movesAvailable = new Vector<Integer>();

    private Vector<Integer> winnerResult = new Vector<Integer>();

    public ControlGameAction() {

        int index = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                gameTrack[row][column] = EMPTY;
                //gameSequnce[index] = 0;
                //gameHistory[index] = EMPTY;
                movesAvailable.add(index++);
            }

        }

    }

    public int[][] getGameTrack() {
        return this.gameTrack;
    }

    public void updateGameTrack(int row, int column, int player) {

        int index = ((row * 3) + (column % 3));

        int j = 0;
        if (gameTrack[row][column] == EMPTY) {
            gameTrack[row][column] = player;
            //gameHistory[sequanceIndex] = index;
            //gameSequnce[sequanceIndex++] = player ;
            j = movesAvailable.indexOf(index);
            movesAvailable.remove(j);

            System.out.println(movesAvailable);
        }

        //movesAvailable.remove(((row * 3) + (column % 3)));
        /*
        for (Integer item : movesAvailable) {
            if (index == item) {
                j = i;
            }
            i++;
        }*/
    }

    public int random() {
        System.out.println("movesAvailable.size(): " + movesAvailable.size());
        int[] moves = new int[movesAvailable.size()];
        int index = 0;

        for (Integer item : movesAvailable) {
            moves[index++] = item;
        }

        int randomMove = moves[new java.util.Random().nextInt(moves.length)];

        return randomMove;
    }

    public int getCompMove() {
        System.out.println("Let me think...");
        TicTacNode node = new TicTacNode(this.gameTrack);
        node = node.findMax();
        return node.move;
    }

    public boolean checkWin(int player) {

        System.out.println("gamehandler.ControlGameAction.checkWin()");
        System.out.println("checkWin for player -> " + player);
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                System.out.println("gameTrack -> " + gameTrack[i][j]);
            }
        }

        boolean win = true;
        //check rows
        for (int i = 0; i < 3; i++) {
            win = true;
            for (int j = 0; j < 3; j++) {
                if (gameTrack[i][j] != player) {
                    win = false;
                } else {
                    colunm[j] = j;
                    row[0] = i;
                    row[1] = i;
                    row[2] = i;
                }
            }
            if (win) {
                view.setWinnerInfo(row, colunm, player);
                return true;
            }

        }

        //check columns
        for (int j = 0; j < 3; j++) {
            win = true;
            for (int i = 0; i < 3; i++) {
                if (gameTrack[i][j] != player) {
                    win = false;
                } else {
                    row[i] = i;
                    colunm[0] = j;
                    colunm[1] = j;
                    colunm[2] = j;
                }
            }
            if (win) {
                view.setWinnerInfo(row, colunm, player);
                return true;
            }
        }

        //check diagonal \
        win = true;
        for (int i = 0, j = 0; i < 3; i++, j++) {
            if (gameTrack[i][j] != player) {
                win = false;
            } else {
                row[i] = i;
                colunm[j] = j;

            }
        }

        if (win) {
            view.setWinnerInfo(row, colunm, player);
            return true;
        }

        //check diagonal /
        win = true;
        for (int i = 0; i < 3; i++) {

            int j = 2 - i;
            if (gameTrack[i][j] != player) {
                win = false;
            } else {
                row[i] = i;
                colunm[i] = j;

            }
        }
        view.setWinnerInfo(row, colunm, player);

        return win;
    }

    public boolean checkDraw() {

        boolean retValue = true;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (gameTrack[row][column] == EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }

    public void resetGameControl() {
        System.out.println("gamehandler.ControlGameAction.resetGameControl()");
        int index = 0;
        movesAvailable.clear();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                gameTrack[row][column] = EMPTY;
                // gameHistory[index] = 0;
                //gameSequnce[index] = EMPTY;
                movesAvailable.add(index++);
                System.out.println("movesAvailable : " + movesAvailable.size());
            }
        }
    }

//    public int[] getGameSequnce(){
//        //return gameSequnce;
//    }
//    
//       public int[] getGameHistory(){
//        //return gameHistory;
//    }
}
