/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.data;

import com.vusieam.academics.deliciouscatering.domain.models.ClientDetails;
import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vusi
 */
public class ClientsDao {

    private final DbContext context = new DbContext();

    public ClientsDao() {
    }

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
            conn = context.createDbConnection();
            PreparedStatement statement = conn.prepareStatement("select * from func_clientAuth('" + username + "','" + password + "');");
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
        } catch (SQLException ex) {
            response.setCode(500);
            response.setStatus(false);
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setCode(500);
            response.setStatus(false);
            response.setMessage(ex.getMessage());
        } finally {
            try {
                context.closeDbConnection(conn);
            } catch (Exception ex) {
                Logger.getLogger(ClientsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

    /**
     * Method to handle creating an account.
     *
     * @param details
     * @return
     */
    public GenericResponse<ClientDetails> createAccount(ClientDetails details) {
        GenericResponse<ClientDetails> response = new GenericResponse<>();
        Connection conn = null;
        try {

            conn = context.createDbConnection();
            String commandText = "call md_createAccount(fname => ?, fsurname => ?, fdateOfBirth => ?, fpasscode => ?, fsecuredPwd => ?, femail => ?, fcell => ?, fphone => ?, fclientId => ?, responseStatus => ?, responseCode => ?,  responseMessage => ?)";//,?,?,?,?
            CallableStatement statement = conn.prepareCall(commandText);
            statement.setString(1, details.getName());
            statement.setString(2, details.getSurname());
            statement.setDate(3, details.getDateOfBirth());
            statement.setString(4, details.getPasscode());
            statement.setString(5, details.getSecuredPassword());
            statement.setString(6, details.getEmail());
            statement.setString(7, details.getCell());
            statement.setString(8, details.getTel());
            statement.registerOutParameter(9, Types.VARCHAR);
            statement.registerOutParameter(10, Types.VARCHAR);
            statement.registerOutParameter(11, Types.VARCHAR);
            statement.registerOutParameter(12, Types.VARCHAR);
            statement.execute();
            
            details.setId(Integer.parseInt(statement.getString(9)));
            response.setStatus(Boolean.parseBoolean(statement.getString(10)));
            response.setCode(Integer.parseInt(statement.getString(11)));
            response.setMessage(statement.getString(12));

            var address = details.getAddress();

            if (address != null) {
                if ((!address.getStreetName().isEmpty()) || (!address.getStreetNumber().isEmpty())) {
                    //var addressStmt = conn.prepareStatement("CALL sp_createAccount(?,?,?,?,?,?,?,?,?,?,?,?)");
                }
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
                context.closeDbConnection(conn);
            } catch (Exception ex) {
                Logger.getLogger(ClientsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

}
