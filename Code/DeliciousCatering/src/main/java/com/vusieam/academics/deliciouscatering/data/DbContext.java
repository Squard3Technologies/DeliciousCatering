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

    public DbContext() {

    }

    //<editor-fold defaultstate="collapsed" desc="database connection section">
    public Connection createDbConnection() throws Exception {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/deliciouscatering", "prgadmin", "Pass123");
        } catch (Exception ex) {
            throw ex;
        }
        return connection;
    }

    public void closeDbConnection(Connection conn) {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (Exception ex) {

        }
    }

    //</editor-fold>
    /**
     * Method to handle user/client login to the system.
     *
     * @param username
     * @param password
     * @param con
     * @return
     */
    public GenericResponse<ClientModel> clientAuthAsync(String username, String password) {
        GenericResponse<ClientModel> response = new GenericResponse<>();
        Connection conn = null;
        try {
            conn = createDbConnection();
            PreparedStatement statement = conn.prepareStatement("select * from func_clientAuth('"+username+"','"+password+"');");
            ResultSet result = statement.executeQuery();
            if (!result.next()) {
                response.setCode(404);
                response.setStatus(false);
                response.setMessage("Login failed, incorrect username or password");
            } else {
                ClientModel model = new ClientModel();
                model.setId(result.getInt("id"));
                model.setName(result.getString("name"));
                model.setSurname(result.getString("surname"));
                model.setDateOfBirth(result.getDate("dateOfBirth"));

                response.setCode(200);
                response.setStatus(true);
                response.setMessage("Login successful");
                response.setData(model);
            }
        } 
        catch (SQLException ex) {
            response.setCode(500);
            response.setStatus(false);
            response.setMessage(ex.getMessage());
        } 
        catch (Exception ex) {
            response.setCode(500);
            response.setStatus(false);
            response.setMessage(ex.getMessage());
        } 
        finally {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

}
