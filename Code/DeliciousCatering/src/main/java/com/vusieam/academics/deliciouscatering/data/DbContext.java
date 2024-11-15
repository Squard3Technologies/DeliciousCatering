package com.vusieam.academics.deliciouscatering.data;

import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre, Elias, Vusi
 */
public class DbContext {
    
    private  String dbUrl = "jdbc:postgresql://localhost:5432/deliciouscatering";
    private  String dbUsername = "prgadmin";
    private  String dbPassword = "Pass123";  
    private  String dbDriver = "org.postgresql.Driver";    

    public DbContext() {

    }

    //<editor-fold defaultstate="collapsed" desc="database connection section">
    
    /**
     * Create a database connection.
     * @return
     * @throws Exception 
     */
    public Connection createDbConnection() throws Exception {
        Connection connection;
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        }          
        catch (SQLException ex) {
            throw ex;
        } 
        catch (Exception ex) {
            throw ex;
        }
        return connection;
    }

    
    
    /**
     * Close an open database connection.
     * @param conn 
     */
    public void closeDbConnection(Connection conn) throws Exception {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } 
        catch (SQLException ex) {
            throw ex;
        } 
        catch (Exception ex) {
            throw ex;
        }
    }

    //</editor-fold>
    
   
}


