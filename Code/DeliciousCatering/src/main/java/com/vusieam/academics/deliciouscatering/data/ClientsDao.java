/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.data;

import com.vusieam.academics.deliciouscatering.domain.models.BookingModel;
import com.vusieam.academics.deliciouscatering.domain.models.BookingPaymentModel;
import com.vusieam.academics.deliciouscatering.domain.models.ClientDetails;
import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.FullBookingModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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
     * Method to handle user/client login to the system.
     *
     * @param username
     * @param password
     * @param con
     * @return
     */
    public GenericResponse<ClientModel> adminAuthAsync(String username, String password) {
        GenericResponse<ClientModel> response = new GenericResponse<>();
        Connection conn = null;
        try {
            conn = context.createDbConnection();
            PreparedStatement statement = conn.prepareStatement("select * from func_adminauth('" + username + "','" + password + "');");
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

    public GenericResponse<BookingModel> createBooking(BookingModel model, Integer clientId) {

        GenericResponse<BookingModel> response = new GenericResponse<>();
        Connection conn = null;
        try {

            conn = context.createDbConnection();
            String commandText = "call public.md_createbooking(fclientId => ?, ftypeofEventId => ?, feventDate => ?, feventTime => ?, fexpectedAdultsAttendance => ?, fexpectedKidsAttendance => ?, femailAddress => ?, fcellMobile => ?, ftelNo => ?, fadultMenuTacos => ?, fadultMenuChickenWrap => ?, fadultMenuChickenKebab => ?, fkidsMenuMiniPizzaCheese => ?, fkidsMenuMiniMiniPizza => ?, fkidsMenuMiniSliders => ?, fkidsMenuMiniHandpie => ?, fmenuDrinksIcetea => ?, fmenuDrinksOrangeJuice => ?, fmenuDrinksAppleJuice => ?, fmenuDrinksFantaOrange => ?,  fmenuDrinksCocacola => ?,  fmenuDrinksApricotJuice => ?,  fmenuDessertOreoPudding => ?,  fmenuDessertOreoBalls => ?,  fmenuDessertChurros => ?,  fmenuDessertDonuts => ?,  fmenuDessertMalva => ?,  fmenuDessertBerry => ?,  fdecoration => ?, fthemeDetails => ?, faddressTypeId => ?, fstreetNumber => ?, fstreetName => ?, fcomplexBuilding => ?, fsurburb => ?, fcity => ?, fzipcode => ?, fprovince => ?, fcountry => ?, referenceNo => ?, responseStatus => ?, responseCode => ?,  responseMessage => ?)";
            CallableStatement statement = conn.prepareCall(commandText);
            statement.setInt(1, clientId);
            statement.setInt(2, model.getTypeofEvent());
            statement.setDate(3, model.getEventDate());
            statement.setTime(4, model.getEventTime());
            statement.setInt(5, model.getAttendingAdults());
            statement.setInt(6, model.getAttendingKids());
            statement.setString(7, model.getEmailAddress());
            statement.setString(8, model.getCellMobile());
            statement.setString(9, model.getTelNo());
            statement.setBoolean(10, model.getAdultMenuTacos());
            statement.setBoolean(11, model.getAdultMenuChickenWrap());
            statement.setBoolean(12, model.getAdultMenuChickenKebab());
            statement.setBoolean(13, model.getKidsMenuMiniPizzaCheese());
            statement.setBoolean(14, model.getKidsMenuMiniMiniPizza());
            statement.setBoolean(15, model.getKidsMenuMiniSliders());
            statement.setBoolean(16, model.getKidsMenuMiniHandpie());
            statement.setBoolean(17, model.getMenuDrinksIcetea());
            statement.setBoolean(18, model.getMenuDrinksOrangeJuice());
            statement.setBoolean(19, model.getMenuDrinksAppleJuice());
            statement.setBoolean(20, model.getMenuDrinksFantaOrange());
            statement.setBoolean(21, model.getMenuDrinksCocacola());
            statement.setBoolean(22, model.getMenuDrinksApricotJuice());
            statement.setBoolean(23, model.getMenuDessertOreoPudding());
            statement.setBoolean(24, model.getMenuDessertOreoBalls());
            statement.setBoolean(25, model.getMenuDessertChurros());
            statement.setBoolean(26, model.getMenuDessertDonuts());
            statement.setBoolean(27, model.getMenuDessertMalva());
            statement.setBoolean(28, model.getMenuDessertBerry());
            statement.setBoolean(29, model.getDecorNeeded());
            statement.setString(30, model.getThemeDetails());

            statement.setInt(31, model.getAddressType());
            statement.setString(32, model.getStreetNo());
            statement.setString(33, model.getStreetName());
            statement.setString(34, model.getComplexName());
            statement.setString(35, model.getSuburb());
            statement.setString(36, model.getCity());
            statement.setString(37, model.getZipCode());
            statement.setString(38, model.getProvince());
            statement.setString(39, model.getCountry());

            statement.registerOutParameter(40, Types.VARCHAR);
            statement.registerOutParameter(41, Types.VARCHAR);
            statement.registerOutParameter(42, Types.VARCHAR);
            statement.registerOutParameter(43, Types.VARCHAR);
            statement.execute();

            model.setId(Integer.parseInt(statement.getString(40)));
            response.setStatus(Boolean.parseBoolean(statement.getString(41)));
            response.setCode(Integer.parseInt(statement.getString(42)));
            response.setMessage(statement.getString(43));

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

    public GenericResponse<BookingPaymentModel> makeBookingPayment(BookingPaymentModel model) {

        GenericResponse<BookingPaymentModel> response = new GenericResponse<>();
        Connection conn = null;
        try {

            conn = context.createDbConnection();
            String commandText = "call public.md_captureBookingPayments(bookingId => ?, paymentDate => ?, paymentAmount => ?, clientComments => ?, responseStatus => ?, responseCode => ?,  responseMessage => ?)";
            CallableStatement statement = conn.prepareCall(commandText);
            statement.setInt(1, model.getBookingId());
            statement.setDate(2, model.getPaymentDate());
            statement.setDouble(3, model.getPaymentAmount());
            statement.setString(4, model.getClientComments());
            statement.registerOutParameter(5, Types.VARCHAR);
            statement.registerOutParameter(6, Types.VARCHAR);
            statement.registerOutParameter(7, Types.VARCHAR);

            statement.execute();

            response.setStatus(Boolean.parseBoolean(statement.getString(5)));
            response.setCode(Integer.parseInt(statement.getString(6)));
            response.setMessage(statement.getString(7));

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

    public GenericResponse<FullBookingModel> getBookingsByClient(Integer clientId) {

        GenericResponse<FullBookingModel> response = new GenericResponse<>();
        Connection conn = null;
        try {

            conn = context.createDbConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM public.md_getbookingsbyclient('" + clientId + "');");
            ResultSet result = statement.executeQuery();
            List<BookingModel> bookings = new ArrayList<>();
            while (result.next()) {
                BookingModel booking = new BookingModel();
                ClientModel client = new ClientModel();
                client.setId(result.getInt("clientid"));
                client.setName(result.getString("name"));
                client.setSurname(result.getString("surname"));
                client.setGender(result.getString("gender"));
                client.setDateOfBirth(result.getDate("dateOfBirth"));
                booking.setClient(client);

                booking.setId(result.getInt("orderId"));
                booking.setTypeofEvent(result.getInt("typeofEventId"));
                booking.setEventDate(result.getDate("eventDate"));
                booking.setEventTime(result.getTime("eventTime"));
                booking.setAttendingAdults(result.getInt("expectedAdultsAttendance"));
                booking.setAttendingKids(result.getInt("expectedKidsAttendance"));
                booking.setEmailAddress(result.getString("emailAddress"));
                booking.setCellMobile(result.getString("cellMobile"));
                booking.setTelNo(result.getString("telNo"));
                booking.setAdultMenuTacos(result.getBoolean("adultMenuTacos"));
                booking.setAdultMenuTacos(result.getBoolean("adultMenuChickenWrap"));
                booking.setAdultMenuTacos(result.getBoolean("adultMenuChickenKebab"));

                booking.setAdultMenuTacos(result.getBoolean("kidsMenuMiniPizzaCheese"));
                booking.setAdultMenuTacos(result.getBoolean("kidsMenuMiniMiniPizza"));
                booking.setAdultMenuTacos(result.getBoolean("kidsMenuMiniSliders"));
                booking.setAdultMenuTacos(result.getBoolean("kidsMenuMiniHandpie"));

                booking.setAdultMenuTacos(result.getBoolean("menuDrinksIcetea"));
                booking.setAdultMenuTacos(result.getBoolean("menuDrinksOrangeJuice"));
                booking.setAdultMenuTacos(result.getBoolean("menuDrinksAppleJuice"));
                booking.setAdultMenuTacos(result.getBoolean("menuDrinksFantaOrange"));
                booking.setAdultMenuTacos(result.getBoolean("menuDrinksCocacola"));
                booking.setAdultMenuTacos(result.getBoolean("menuDrinksApricotJuice"));
                booking.setAdultMenuTacos(result.getBoolean("menuDessertOreoPudding"));
                booking.setAdultMenuTacos(result.getBoolean("menuDessertOreoBalls"));
                booking.setAdultMenuTacos(result.getBoolean("menuDessertChurros"));

                booking.setAdultMenuTacos(result.getBoolean("menuDessertDonuts"));
                booking.setAdultMenuTacos(result.getBoolean("menuDessertMalva"));
                booking.setAdultMenuTacos(result.getBoolean("menuDessertBerry"));

                booking.setAdultMenuTacos(result.getBoolean("decoration"));
                booking.setThemeDetails(result.getString("themeDetails"));

                booking.setQuoteAmount(result.getString("quoteAmount"));
                booking.setDiscountPercent(result.getString("discountpercent"));
                booking.setFinalQuoteAmount(result.getString("finalQuoteAmount"));
                booking.setCurrentBalance(result.getString("currentBalance"));

                bookings.add(booking);

            }

            if (!bookings.isEmpty()) {
                FullBookingModel bookingResponseData = new FullBookingModel();
                bookingResponseData.setBooking(bookings);
                response.setCode(200);
                response.setStatus(true);
                response.setMessage("Successful");
                response.setData(bookingResponseData);
            } else {
                response.setCode(404);
                response.setStatus(false);
                response.setMessage("No bookings found");
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

}
