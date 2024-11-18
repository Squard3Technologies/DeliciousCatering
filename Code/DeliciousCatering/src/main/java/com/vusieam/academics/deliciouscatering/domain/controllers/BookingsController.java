package com.vusieam.academics.deliciouscatering.domain.controllers;

import com.google.gson.Gson;
import com.vusieam.academics.deliciouscatering.data.ClientsDao;
import com.vusieam.academics.deliciouscatering.domain.models.BookingModel;
import com.vusieam.academics.deliciouscatering.domain.models.ClientDetails;
import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalTime;

/**
 *
 * @author vusi
 */
@WebServlet(name = "bookings", urlPatterns = {"/bookings"})
public class BookingsController extends HttpServlet {

    private final ClientsDao dao = new ClientsDao();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String userJson = (String) session.getAttribute("clientProfile");
            ClientModel client = new Gson().fromJson(userJson, ClientModel.class);

            BookingModel model = new BookingModel();
            model.setTypeofEvent(Integer.parseInt(request.getParameter("txtEventType")));
            model.setEventDate(java.sql.Date.valueOf(request.getParameter("eventDate")));
            var tm = request.getParameter("eventTime");
            LocalTime ltm = LocalTime.parse(tm);
            var ttmt = java.sql.Time.valueOf(ltm);

            model.setEventTime(java.sql.Time.valueOf(ltm));

            model.setAttendingAdults(Integer.parseInt(request.getParameter("txtAdults")));
            model.setAttendingKids(Integer.parseInt(request.getParameter("txtKids")));

            var chbxDecorationNeeded = request.getParameter("chbxDecorationNeeded") == "on";
            model.setDecorNeeded(Boolean.parseBoolean(request.getParameter("chbxDecorationNeeded")));
            model.setThemeDetails((request.getParameter("txtThemeDetails")));

            model.setEmailAddress((request.getParameter("txtEmail")));
            model.setCellMobile((request.getParameter("txtCell")));
            model.setTelNo((request.getParameter("txtTelNo")));

            model.setAddressType(Integer.parseInt(request.getParameter("txtAddressType")));
            model.setStreetNo((request.getParameter("txtStreetNo")));
            model.setStreetName((request.getParameter("txtStreetName")));
            model.setComplexName((request.getParameter("txtComplex")));
            model.setSuburb((request.getParameter("txtSuburb")));
            model.setCity((request.getParameter("txtCity")));
            model.setZipCode((request.getParameter("txtZip")));
            model.setProvince((request.getParameter("txtProvince")));
            model.setCountry((request.getParameter("txtCountry")));

            var chbxAdultMenuTacos = request.getParameter("chbxAdultMenuTacos") == "on";
            var chbxAdultMenuChickenWrap = request.getParameter("chbxAdultMenuChickenWrap") == "on";
            var chbxAdultMenuChickenKebab = request.getParameter("chbxAdultMenuChickenKebab") == "on";
            model.setAdultMenuTacos(chbxAdultMenuTacos);
            model.setAdultMenuChickenWrap(chbxAdultMenuChickenWrap);
            model.setAdultMenuChickenKebab(chbxAdultMenuChickenKebab);

            var chbxKidsMenuMiniPizzaCheese = request.getParameter("chbxKidsMenuMiniPizzaCheese") == "on";
            var chbxKidsMenuMiniPizza = request.getParameter("chbxKidsMenuMiniPizza") == "on";
            var chbxKidsMenuSliders = request.getParameter("chbxKidsMenuSliders") == "on";
            var chbxKidsMenuHandpie = request.getParameter("chbxKidsMenuHandpie") == "on";
            model.setKidsMenuMiniPizzaCheese(chbxKidsMenuMiniPizzaCheese);
            model.setKidsMenuMiniMiniPizza(chbxKidsMenuMiniPizza);
            model.setKidsMenuMiniSliders(chbxKidsMenuSliders);
            model.setKidsMenuMiniHandpie(chbxKidsMenuHandpie);

            var chbxDrinksIcetea = request.getParameter("chbxDrinksIcetea") == "on";
            var chbxDrinksOrangeJuice = request.getParameter("chbxDrinksOrangeJuice") == "on";
            var chbxDrinksAppleJuice = request.getParameter("chbxDrinksAppleJuice") == "on";
            var chbxDrinksFantaOrange = request.getParameter("chbxDrinksFantaOrange") == "on";
            var chbxDrinksCocacola = request.getParameter("chbxDrinksCocacola") == "ON";
            var chbxDrinksApricotJuice = request.getParameter("chbxDrinksApricotJuice") == "on";
            model.setMenuDrinksIcetea(chbxDrinksIcetea);
            model.setMenuDrinksOrangeJuice(chbxDrinksOrangeJuice);
            model.setMenuDrinksAppleJuice(chbxDrinksAppleJuice);
            model.setMenuDrinksFantaOrange(chbxDrinksFantaOrange);
            model.setMenuDrinksCocacola(chbxDrinksCocacola);
            model.setMenuDrinksApricotJuice(chbxDrinksApricotJuice);

            var chbxDessertOreoPudding = request.getParameter("chbxDessertOreoPudding") == "on";
            var chbxDessertOreoBalls = request.getParameter("chbxDessertOreoBalls") == "on";
            var chbxDessertChurros = request.getParameter("chbxDessertChurros") == "on";
            var chbxDessertDonuts = request.getParameter("chbxDessertDonuts") == "on";
            var chbxDessertMalva = request.getParameter("chbxDessertMalva") == "on";
            var chbxDessertBerry = request.getParameter("chbxDessertBerry") == "on";
            model.setMenuDessertOreoPudding(chbxDessertOreoPudding);
            model.setMenuDessertOreoBalls(chbxDessertOreoBalls);
            model.setMenuDessertChurros(chbxDessertChurros);
            model.setMenuDessertDonuts(chbxDessertDonuts);
            model.setMenuDessertMalva(chbxDessertMalva);
            model.setMenuDessertBerry(chbxDessertBerry);
            GenericResponse<BookingModel> createResponse = dao.createBooking(model, client.getId());
            String json = new Gson().toJson(createResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);            
        } 
        catch (Exception ex) {
            GenericResponse<ClientDetails> createResponse = new GenericResponse();
            createResponse.setStatus(false);
            createResponse.setCode(500);
            createResponse.setMessage(ex.getMessage() + "<br/>" + ex.getStackTrace());
            String json = new Gson().toJson(createResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
