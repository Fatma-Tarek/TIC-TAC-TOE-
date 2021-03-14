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
public class DatabaseCreation {
    
    // create database if dosen't exists
    
    public DatabaseCreation() {
        DatabaseConnection dbc = new DatabaseConnection();
        
        try{
            Connection connect = dbc.connectTOServer();
            
            PreparedStatement pStmt = connect.prepareStatement("CREATE DATABASE IF NOT EXISTS tictactoe");
            int status = pStmt.executeUpdate();
            
            if(status == 1) {
                System.out.println("Database Created");
            } else {
                System.out.println("Database Already Exist");
            }
            
        } catch(SQLException ex)
        {
            ex.printStackTrace();
        } finally {
            dbc.closeConnection();
        }
        
    }
}
