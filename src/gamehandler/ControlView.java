/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamehandler;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author Al-Qirawan
 */
public class ControlView {

    public static Label[][] viewArray = new Label[3][3];

    private Label[] signupErrorMessges = new Label[4];

    private Label[] secondPlayerMessge = new Label[2];

    private Label loginErrorMessges;

    private Button playAgain;
    private Button replayAgain;

    /*
    final int nameError = 0;
    final int mailError = 1;
    final int passError = 2;
    final int passError2 = 3;
     */
    final int nameErrorMess = 0;
    final int mailErrorMess = 1;
    final int passErrorMess = 2;
    final int passErrorMess2 = 3;

    final int secondPlayerName = 0;

    final int userName = 1;

    final int userScore = 2;
    final int gameInfo = 3;

    final int secondPlayerNameError = 0;
    final int secondPlayerNameErrorMess = 1;

    /*
    private Label secondPlayerName;
    private Label secondPlayerLetter;
    private Label userName;
    private Label userF_Letter;
    private Label userScore;
    private Label secondPlayerScore;
    private Label gameInfo;
     */
    public Label[] globalGameInfo;

    public static int[] winnerArray_row = new int[3];
    public static int[] winnerArray_column = new int[3];

    static int winnerPlayer = -1;

    public ControlView() {

    }

    public ControlView(Label loginErrorMessges) {
        this.loginErrorMessges = loginErrorMessges;
    }

    public ControlView(Label[] errorMessges) {

        this.signupErrorMessges = errorMessges;
    }

    public ControlView(Label[][] viewArray, Button save, Button playAgain, Label[] globalGameInfo) {

        System.out.println("gamehandler.ControlView.<init>()");
        this.globalGameInfo = new Label[globalGameInfo.length];

        this.viewArray = viewArray;
        this.playAgain = playAgain;
        this.replayAgain = save;
        this.globalGameInfo = globalGameInfo;
    }

    public void initiVeiw(String user1, String user2, boolean turn) {

        System.out.println(user2);
        globalGameInfo[secondPlayerName].setText(user2);

        globalGameInfo[userName].setText(user1);
        resetVeiw();

        setTurnMessage(user1, user2, turn);

    }

    public void setUserName(String user) {

        globalGameInfo[userName].setText(user);

    }

    public void setUser2Name(String user) {

        globalGameInfo[secondPlayerName].setText(user);

    }

    public int updateVeiw(int row, int column, int player) {

        System.out.println("gamehandler.ControlView.updateVeiw()");
        if ("".equals(viewArray[row][column].getText())) {

            String str = player == ControlGameAction.X ? "X" : "O";

            String color = player == ControlGameAction.X ? "#f28113" : "#13476b";
            viewArray[row][column].setText(str);
            globalGameInfo[gameInfo].setText(player == ControlGameAction.X
                    ? globalGameInfo[secondPlayerName].getText() + " Turn"
                    : globalGameInfo[userName].getText() + " Turn");

            viewArray[row][column].setTextFill(Color.valueOf(color));

        }
        return 1;

    }

    public int updateVeiwWinner(int row, int column, int player) {

        System.out.println("gamehandler.ControlView.updateVeiwWinner()");
        if ("".equals(viewArray[row][column].getText())) {

            String str = player == ControlGameAction.X ? "X" : "O";

            String color = player == ControlGameAction.X ? "#f28113" : "#13476b";
            viewArray[row][column].setText(str);

            viewArray[row][column].setTextFill(Color.valueOf(color));

        }
        return 1;

    }

    public String getCell(int row, int column) {
        return viewArray[row][column].getText();
    }

    public void resetVeiw() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                viewArray[i][j].setText("");
            }
            viewArray[winnerArray_row[i]][winnerArray_column[i]].setTextFill(
                    Color.valueOf("#FFF"));
        }
        globalGameInfo[gameInfo].setText("");

    }

    public void winVeiw() {

        globalGameInfo[gameInfo].setText(winnerPlayer == ControlGameAction.X
                ? globalGameInfo[userName].getText() + " Wins"
                : globalGameInfo[secondPlayerName].getText() + " Wins");
        for (int i = 0; i < winnerArray_row.length; i++) {

            viewArray[winnerArray_row[i]][winnerArray_column[i]].setTextFill(
                    javafx.scene.paint.Color.valueOf("#f8d63f"));

        }

    }

    public void winClientVeiw(int[] location) {

        System.out.println("location.length -> " + location.length);
        for (int i = 0; i < location.length; i++) {
            winnerArray_row[i] = location[i] / 3;
            winnerArray_column[i] = location[i] % 3;

            System.out.println("location -> " + location[i]);
            viewArray[winnerArray_row[i]][winnerArray_column[i]].setTextFill(
                    javafx.scene.paint.Color.valueOf("#f8d63f"));

        }

    }

    public void setWinnerInfo(int[] row, int[] column, int player) {

        this.winnerPlayer = player;

        this.winnerArray_row = row;

        this.winnerArray_column = column;

    }

    public void setTurnMessage(String user1, String user2, boolean turn) {

        globalGameInfo[gameInfo].setText(turn ? user1 + " turn" : user2 + " turn");

    }

    public void messageToInfo(String message) {

        globalGameInfo[gameInfo].setText(message);

    }

    public void updateScore(int score1, int score2, int player) {

        if (player == ControlGameAction.X) {

            globalGameInfo[userScore].setText(Integer.toString(score1));
        } else {
            // globalGameInfo[secondPlayerScore].setText(Integer.toString(score2));
        }
    }

    public void showGameNameError() {
        this.loginErrorMessges.setVisible(true);
    }

    public void hideGameNameError() {
        this.loginErrorMessges.setVisible(false);
    }

    public void showPlayAgainButton() {
        playAgain.setVisible(true);
        //save.setVisible(true);
    }

    public void hidePlayAgainButton() {
        playAgain.setVisible(false);
        //save.setVisible(false);
    }

    public void showReplayAgainButton() {
        //playAgain.setVisible(true);
        replayAgain.setVisible(true);
    }

    public void hideReplayAgainButton() {
        //playAgain.setVisible(false);
        replayAgain.setVisible(false);
    }

    public void showLoginErrorMessage() {
        loginErrorMessges.setVisible(true);
    }

    public void hideLoginErrorMessage() {
        loginErrorMessges.setVisible(false);
    }

    public void showSignupNameErrorMessage() {
        //signupErrorMessges[nameError].setVisible(true);
        signupErrorMessges[nameErrorMess].setVisible(true);
    }

    public void hideSignupNameErrorMessage() {
        //signupErrorMessges[nameError].setVisible(false);
        signupErrorMessges[nameErrorMess].setVisible(false);
    }

    public void showSignupMailErrorMessage() {
        //signupErrorMessges[mailError].setVisible(true);
        signupErrorMessges[mailErrorMess].setVisible(true);
    }

    public void hideSignupMailErrorMessage() {
        //signupErrorMessges[mailError].setVisible(false);
        signupErrorMessges[mailErrorMess].setVisible(false);
    }

    public void showSignupPassErrorMessage() {
        //signupErrorMessges[passError].setVisible(true);
        signupErrorMessges[passErrorMess].setVisible(true);
    }

    public void hideSignupPassErrorMessage() {
        //signupErrorMessges[passError].setVisible(false);
        signupErrorMessges[passErrorMess].setVisible(false);
    }

    public void showSignupPass2ErrorMessage() {
        //signupErrorMessges[passError2].setVisible(true);
        signupErrorMessges[passErrorMess2].setVisible(true);
    }

    public void hideSignupPass2ErrorMessage() {
        //signupErrorMessges[passError2].setVisible(false);
        signupErrorMessges[passErrorMess2].setVisible(false);
    }

    public void showErrorMessages(int errorCode) {

        switch (errorCode) {
            case 0:
                showSignupNameErrorMessage();
                break;
            case 1:
                showSignupMailErrorMessage();
                break;

            case 2:
                showSignupPassErrorMessage();
                break;
            case 3:
                showSignupPass2ErrorMessage();
                break;

        }

    }

    public void showSecondPlayerNameErrorMessage() {
        //signupErrorMessges[secondPlayerNameError].setVisible(true);
        signupErrorMessges[secondPlayerNameErrorMess].setVisible(true);
    }

    public void hideSecondPlayerNameErrorMessage() {
        //signupErrorMessges[secondPlayerNameError].setVisible(false);
        signupErrorMessges[secondPlayerNameErrorMess].setVisible(false);
    }

}
