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
public class UserTableCreation {
    
    // create user table if dosen't exists
    
    public UserTableCreation() {
        DatabaseConnection dbc = new DatabaseConnection();
        
        try{
            Connection connect = dbc.startConnection();
            
            PreparedStatement pStmt = connect.prepareStatement("CREATE TABLE IF NOT EXISTS tictactoe.users ( id INT(11) PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, status INT(11), score INT(11)) ENGINE = InnoDB;");
            int status = pStmt.executeUpdate();
            
            if(status == 1) {
                System.out.println("User Table Created");
            } else {
                System.out.println("User Table Already Exist");
            }
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
    }
}
