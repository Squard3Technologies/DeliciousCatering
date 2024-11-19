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
public class CreateBookingsController extends HttpServlet {

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

            if (request.getParameter("chbxDecorationNeeded") != null && !request.getParameter("chbxDecorationNeeded").isEmpty()) {
                var chbxDecorationNeeded22 = request.getParameter("chbxDecorationNeeded");
                Boolean chbxDecorationNeeded = request.getParameter("chbxDecorationNeeded").equalsIgnoreCase("on");
                model.setDecorNeeded(chbxDecorationNeeded);
            }
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

            if (request.getParameter("chbxAdultMenuTacos") != null && !request.getParameter("chbxAdultMenuTacos").isEmpty()) {
                var chbxAdultMenuTacos = request.getParameter("chbxAdultMenuTacos").equalsIgnoreCase("on");
                model.setAdultMenuTacos(chbxAdultMenuTacos);
            } else {
                model.setAdultMenuTacos(false);
            }

            if (request.getParameter("chbxAdultMenuChickenWrap") != null && !request.getParameter("chbxAdultMenuChickenWrap").isEmpty()) {
                var chbxAdultMenuChickenWrap = request.getParameter("chbxAdultMenuChickenWrap").equalsIgnoreCase("on");
                model.setAdultMenuChickenWrap(chbxAdultMenuChickenWrap);
            } else {
                model.setAdultMenuChickenWrap(false);
            }

            if (request.getParameter("chbxAdultMenuChickenKebab") != null && !request.getParameter("chbxAdultMenuChickenKebab").isEmpty()) {
                var chbxAdultMenuChickenKebab = request.getParameter("chbxAdultMenuChickenKebab").equalsIgnoreCase("on");
                model.setAdultMenuChickenKebab(chbxAdultMenuChickenKebab);

            } else {
                model.setAdultMenuChickenKebab(false);
            }

            if (request.getParameter("chbxKidsMenuMiniPizzaCheese") != null && !request.getParameter("chbxKidsMenuMiniPizzaCheese").isEmpty()) {
                var chbxKidsMenuMiniPizzaCheese = request.getParameter("chbxKidsMenuMiniPizzaCheese").equalsIgnoreCase("on");
                model.setKidsMenuMiniPizzaCheese(chbxKidsMenuMiniPizzaCheese);
            }

            if (request.getParameter("chbxKidsMenuMiniPizza") != null && !request.getParameter("chbxKidsMenuMiniPizza").isEmpty()) {
                var chbxKidsMenuMiniPizza = request.getParameter("chbxKidsMenuMiniPizza").equalsIgnoreCase("on");
                model.setKidsMenuMiniMiniPizza(chbxKidsMenuMiniPizza);
            }

            if (request.getParameter("chbxKidsMenuSliders") != null && !request.getParameter("chbxKidsMenuSliders").isEmpty()) {
                var chbxKidsMenuSliders = request.getParameter("chbxKidsMenuSliders").equalsIgnoreCase("on");
                model.setKidsMenuMiniSliders(chbxKidsMenuSliders);
            }

            if (request.getParameter("chbxKidsMenuHandpie") != null && !request.getParameter("chbxKidsMenuHandpie").isEmpty()) {
                var chbxKidsMenuHandpie = request.getParameter("chbxKidsMenuHandpie").equalsIgnoreCase("on");
                model.setKidsMenuMiniHandpie(chbxKidsMenuHandpie);

            }

            if (request.getParameter("chbxDrinksIcetea") != null && !request.getParameter("chbxDrinksIcetea").isEmpty()) {
                var chbxDrinksIcetea = request.getParameter("chbxDrinksIcetea").equalsIgnoreCase("on");
                model.setMenuDrinksIcetea(chbxDrinksIcetea);
            }

            if (request.getParameter("chbxDrinksOrangeJuice") != null && !request.getParameter("chbxDrinksOrangeJuice").isEmpty()) {
                var chbxDrinksOrangeJuice = request.getParameter("chbxDrinksOrangeJuice").equalsIgnoreCase("on");
                model.setMenuDrinksOrangeJuice(chbxDrinksOrangeJuice);
            }

            if (request.getParameter("chbxDrinksAppleJuice") != null && !request.getParameter("chbxDrinksAppleJuice").isEmpty()) {
                var chbxDrinksAppleJuice = request.getParameter("chbxDrinksAppleJuice").equalsIgnoreCase("on");
                model.setMenuDrinksAppleJuice(chbxDrinksAppleJuice);
            }

            if (request.getParameter("chbxDrinksFantaOrange") != null && !request.getParameter("chbxDrinksFantaOrange").isEmpty()) {
                var chbxDrinksFantaOrange = request.getParameter("chbxDrinksFantaOrange").equalsIgnoreCase("on");
                model.setMenuDrinksFantaOrange(chbxDrinksFantaOrange);
            }

            if (request.getParameter("chbxDrinksCocacola") != null && !request.getParameter("chbxDrinksCocacola").isEmpty()) {
                var chbxDrinksCocacola = request.getParameter("chbxDrinksCocacola").equalsIgnoreCase("on");
                model.setMenuDrinksCocacola(chbxDrinksCocacola);
            }

            if (request.getParameter("chbxDrinksApricotJuice") != null && !request.getParameter("chbxDrinksApricotJuice").isEmpty()) {
                var chbxDrinksApricotJuice = request.getParameter("chbxDrinksApricotJuice").equalsIgnoreCase("on");
                model.setMenuDrinksApricotJuice(chbxDrinksApricotJuice);
            }

            if (request.getParameter("chbxDessertOreoPudding") != null && !request.getParameter("chbxDessertOreoPudding").isEmpty()) {
                var chbxDessertOreoPudding = request.getParameter("chbxDessertOreoPudding").equalsIgnoreCase("on");
                model.setMenuDessertOreoPudding(chbxDessertOreoPudding);
            }

            if (request.getParameter("chbxDessertOreoBalls") != null && !request.getParameter("chbxDessertOreoBalls").isEmpty()) {
                var chbxDessertOreoBalls = request.getParameter("chbxDessertOreoBalls").equalsIgnoreCase("on");
                model.setMenuDessertOreoBalls(chbxDessertOreoBalls);
            }

            if (request.getParameter("chbxDessertChurros") != null && !request.getParameter("chbxDessertChurros").isEmpty()) {
                var chbxDessertChurros = request.getParameter("chbxDessertChurros").equalsIgnoreCase("on");
                model.setMenuDessertChurros(chbxDessertChurros);
            }

            if (request.getParameter("chbxDessertDonuts") != null && !request.getParameter("chbxDessertDonuts").isEmpty()) {
                var chbxDessertDonuts = request.getParameter("chbxDessertDonuts").equalsIgnoreCase("on");
                model.setMenuDessertDonuts(chbxDessertDonuts);
            }

            if (request.getParameter("chbxDessertMalva") != null && !request.getParameter("chbxDessertMalva").isEmpty()) {
                var chbxDessertMalva = request.getParameter("chbxDessertMalva").equalsIgnoreCase("on");
                model.setMenuDessertMalva(chbxDessertMalva);
            }

            if (request.getParameter("chbxDessertBerry") != null && !request.getParameter("chbxDessertBerry").isEmpty()) {
                var chbxDessertBerry = request.getParameter("chbxDessertBerry").equalsIgnoreCase("on");
                model.setMenuDessertBerry(chbxDessertBerry);
            }

            GenericResponse<BookingModel> createResponse = dao.createBooking(model, client.getId());
            String json = new Gson().toJson(createResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception ex) {
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
