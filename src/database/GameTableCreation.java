/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author a8585_000
 */
public class GameTableCreation {
    
    public GameTableCreation() {
        DatabaseConnection dbc = new DatabaseConnection();
        
        try{
            Connection connect = dbc.startConnection();
            
            PreparedStatement pStmt = connect.prepareStatement("CREATE TABLE IF NOT EXISTS tictactoe.games ( id INT(11) PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), player1_id INT(11), player2_id INT(11), dateTime TIMESTAMP DEFAULT NOW(), FOREIGN KEY(player1_id) REFERENCES users(id), FOREIGN KEY(player2_id) REFERENCES users(id)) ENGINE = InnoDB;");
            int status = pStmt.executeUpdate();
            System.out.println("Game Table Created or already exists");
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
}
