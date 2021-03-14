/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author a8585_000
 */
public class User {

    private int id = 0;
    private String name;
    private String email;
    private int status;
    private int score;
    
    private int clentScore = 0;

    public void setScore(int _score) {
        this.score = _score;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getStatus() {
        return this.status;
    }

    public int getScore() {
        return this.score;
    }

    //register new user and return list of error if registration is falied 
    public static ArrayList<Integer> userRegister(String name, String email, String password, String passwordConfirmation) {

        ArrayList<Integer> errors = new ArrayList<>();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (name.equals("")) {
            errors.add(0);
        }

        if (!pat.matcher(email).matches()) {
            errors.add(1);
        }

        if (password.equals("")) {
            errors.add(2);
        }

        if (!password.equals(passwordConfirmation)) {
            errors.add(3);
        }

        if (errors.isEmpty()) {

            DatabaseConnection dbc = new DatabaseConnection();

            try {
                Connection connect = dbc.startConnection();

                PreparedStatement insertStmt = connect.prepareStatement("INSERT INTO users(name, email, password) VALUES(?, ?, ?)");
                insertStmt.setString(1, name);
                insertStmt.setString(2, email);
                insertStmt.setString(3, password);
                int status = insertStmt.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                dbc.closeConnection();
            }

        }

        return (errors);
    }

    // user login function
    public void userLogin(String email, String password) {
        DatabaseConnection dbc = new DatabaseConnection();
        try {
            Connection connect = dbc.startConnection();
            PreparedStatement selectStmt = connect.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            selectStmt.setString(1, email);
            selectStmt.setString(2, password);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                this.id = rs.getInt("id");
                this.name = rs.getString("name");
                this.email = rs.getString("email");
                this.status = rs.getInt("status");
                this.score = rs.getInt("score");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }

    //update score for specific user
    public  int updateScore(int id, int value) {
        DatabaseConnection dbc = new DatabaseConnection();
        this.score += value;
        System.out.println("this.score: " + this.score);
        try {
            Connection connect = dbc.startConnection();
            PreparedStatement updateStmt = connect.prepareStatement("UPDATE users SET score = ? WHERE id = ?");
            updateStmt.setInt(1, this.score);
            updateStmt.setInt(2, id);
            int status = updateStmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return status;
    }
    
    //update score for specific user
    public int updateClientScore(int id, int value) {
        DatabaseConnection dbc = new DatabaseConnection();
        
        System.out.println("this.score: " + this.score);
        try {
            Connection connect = dbc.startConnection();
            PreparedStatement updateStmt = connect.prepareStatement("UPDATE users SET score = ? WHERE id = ?");
            updateStmt.setInt(1, value);
            updateStmt.setInt(2, id);
            int status = updateStmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return status;
    }

    public static String getPlayerName(int playerId) {
        DatabaseConnection dbc = new DatabaseConnection();
        String playerName = "";
        try {
            Connection connect = dbc.startConnection();
            PreparedStatement selectStmt = connect.prepareStatement("SELECT name FROM users WHERE id = ?");
            selectStmt.setInt(1, playerId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                playerName = rs.getString("name");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return playerName;
    }
    
    public static int getPlayerScore(int playerId) {
        DatabaseConnection dbc = new DatabaseConnection();
        int playerScore = 0;
        try {
            Connection connect = dbc.startConnection();
            PreparedStatement selectStmt = connect.prepareStatement("SELECT score FROM users WHERE id = ?");
            selectStmt.setInt(1, playerId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                playerScore = rs.getInt("score");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return playerScore;
    }
}
