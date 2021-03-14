/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author a8585_000
 */
public class Game {
    
    private int id = 0;
    private String name;
    private int player1Id;
    private int player2Id;
    private String dateTime;
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPlayer1Id() {
        return this.player1Id;
    }
    
    public int getPlayer2Id() {
        return this.player2Id;
    }
    
    public String getDateTime() {
        return this.dateTime;
    }
    
    public static int addGame(String name, int player1_id, int player2_id) {
        DatabaseConnection dbc = new DatabaseConnection();
        int createdGameId = 0;
        try{
            Connection connect = dbc.startConnection();

            PreparedStatement insertStmt = connect.prepareStatement("INSERT INTO games(name, player1_id, player2_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, name);
            insertStmt.setInt(2, player1_id);
            insertStmt.setInt(3, player2_id);
            int status = insertStmt.executeUpdate();
            ResultSet rs = insertStmt.getGeneratedKeys();
            if(rs.next()) {
                createdGameId = rs.getInt(1);
            }



        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return createdGameId;
    }
    
    public void getGameById(int id) {
        DatabaseConnection dbc = new DatabaseConnection();
        try{
            Connection connect = dbc.startConnection();
            PreparedStatement selectStmt = connect.prepareStatement("SELECT * FROM games WHERE id = ?");
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();
            
            if(rs.next())
            {
                this.id = rs.getInt("id");
                this.name = rs.getString("name");
                this.player1Id = rs.getInt("player1_id");
                this.player2Id = rs.getInt("player2_id");
                this.dateTime = rs.getString("dateTime");
            }
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
    
    public static ArrayList<Game> GamesForPlayer(int id) {
        ArrayList<Game> games = new ArrayList<>();
        DatabaseConnection dbc = new DatabaseConnection();
        try{
            Connection connect = dbc.startConnection();
            PreparedStatement selectStmt = connect.prepareStatement("SELECT * FROM games WHERE player1_id = ? OR player2_id = ?");
            selectStmt.setInt(1, id);
            selectStmt.setInt(2, id);
            ResultSet rs = selectStmt.executeQuery();
            
            while(rs.next())
            {
                Game game = new Game();
                game.id = rs.getInt("id");
                game.name = rs.getString("name");
                game.player1Id = rs.getInt("player1_id");
                game.player2Id = rs.getInt("player2_id");
                game.dateTime = rs.getString("dateTime");
                games.add(game);
            }
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return (games);
    }
    
}
