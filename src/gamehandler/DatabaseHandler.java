/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamehandler;

import database.*;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Al-Qirawan
 */
public class DatabaseHandler {

    public static int id = 0;
    public static String name;
    private String email;
    private int status;
    public static int score;
    public static User loginUser;
    private static int newScore;

     public static int getId() {
        return id;
    }

    public String getName() {
        return DatabaseHandler.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getStatus() {
        return this.status;
    }

    public int getScore() {
        return DatabaseHandler.score;
    }

    public void setScore(int score) {
        DatabaseHandler.newScore = score;
    }

    public DatabaseHandler() {
        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DatabaseCreation dc1 = new DatabaseCreation();
        UserTableCreation utc1 = new UserTableCreation();

        new GameTableCreation();
        new MoveTableCreation();
    }

    public ArrayList<Integer> register(String userName, String email, String pass1, String pass2) {
        User registerUser = new User();
        ArrayList<Integer> errors = User.userRegister(userName, email, pass1, pass2);

        if (errors.isEmpty()) {
            System.out.println("User registered successfuly");
        } else {
            for (Integer error : errors) {
                System.out.println(error);
            }
        }
        return errors;
    }

    public int login(String email, String pass) {

        this.id = -1;
        loginUser = new User();

        loginUser.userLogin(email, pass);

        if (loginUser.getId() > 0) {
            this.id = loginUser.getId();
            DatabaseHandler.name = loginUser.getName();
            DatabaseHandler.score = loginUser.getScore();
            System.out.println(loginUser.getId());
            System.out.println(loginUser.getScore());
        } else {
            System.out.println("Invalid login, email or password is incorrect");
        }
        return this.id;
    }

    public void updateScore(int id) {

        loginUser.updateScore(id, 1);
        DatabaseHandler.score = getPlayerScore(id);
        System.out.println("score : " + score + "loginUser ID : " + id);

    }
    
      public void updateClientScore(int id ,int score) {

        loginUser.updateClientScore(id, score);
        DatabaseHandler.score = getPlayerScore(id);
        System.out.println("score : " + score + "loginUser ID : " + id);

    }
    

    public String getPlayerName(int playerId) {
        return User.getPlayerName(playerId);
    }

    public int getPlayerScore(int playerId) {
        return User.getPlayerScore(playerId);
    }

}
