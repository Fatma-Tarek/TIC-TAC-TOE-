/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author a8585_000
 */
public class Move {
    
    private int id;
    private String symbolxo;
    private int location;
    private int gameId;
    
    public Move(String _symbolxo, int _location, int _gameId) {
        this.symbolxo = _symbolxo;
        this.location = _location;
        this.gameId = _gameId;
    }
    
    public Move(int _id, String _symbolxo, int _location, int _gameId) {
        this.id = _id;
        this.symbolxo = _symbolxo;
        this.location = _location;
        this.gameId = _gameId;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getSymbolxo() {
        return this.symbolxo;
    }
    
    public int getLocation() {
        return this.location;
    }
    
    public int getGameId() {
        return this.gameId;
    }
    
    public static int addMove(String symbolxo, int location, int game_id) {
        DatabaseConnection dbc = new DatabaseConnection();
        int status = 0;
        try{
            Connection connect = dbc.startConnection();

            PreparedStatement insertStmt = connect.prepareStatement("INSERT INTO moves(symbolxo, location, game_id) VALUES(?, ?, ?)");
            insertStmt.setString(1, symbolxo);
            insertStmt.setInt(2, location);
            insertStmt.setInt(3, game_id);
            status = insertStmt.executeUpdate();

        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return status;
    }
    
    public static ArrayList<Move> movesForGame(int id) {
        ArrayList<Move> moves = new ArrayList<>();
        DatabaseConnection dbc = new DatabaseConnection();
        try{
            Connection connect = dbc.startConnection();
            PreparedStatement selectStmt = connect.prepareStatement("SELECT * FROM moves WHERE game_id = ?");
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();
            
            while(rs.next())
            {
                Move move = new Move(rs.getInt("id"), rs.getString("symbolxo"), rs.getInt("location"), rs.getInt("game_id"));
                moves.add(move);
            }
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        return (moves);
    }
}
