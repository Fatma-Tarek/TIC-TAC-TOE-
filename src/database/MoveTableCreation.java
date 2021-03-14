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
public class MoveTableCreation {
    
    public MoveTableCreation() {
        DatabaseConnection dbc = new DatabaseConnection();
        
        try{
            Connection connect = dbc.startConnection();
            
            PreparedStatement pStmt = connect.prepareStatement("CREATE TABLE IF NOT EXISTS tictactoe.moves( id INT(11) PRIMARY KEY AUTO_INCREMENT, symbolxo VARCHAR(1), location INT(1), game_id INT(11), FOREIGN KEY(game_id) REFERENCES games(id))ENGINE = InnoDB;");
            int status = pStmt.executeUpdate();
            System.out.println("Move Table Created or already exists");
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
}
