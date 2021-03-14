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
public class DatabaseConnection {
    
    Connection connect;
    
    /**
    * ensure that MySQL driver is included
    * connectTOServer used only once to dealing with server if database not exists
    * connectTOServer used in database creation class only
    * use should put your username and password in connectTOServer, startConnection
    */
    
    public Connection connectTOServer() throws SQLException
    {
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost/", "egila", "0000");
            System.out.println("Connection Successuful");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return connect;
        
    }
    
    public Connection startConnection() throws SQLException
    {
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe", "egila", "0000");
            System.out.println("Connection Successuful");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return connect;
        
    }
    
    public void closeConnection()
    {
        try{
            connect.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
}
