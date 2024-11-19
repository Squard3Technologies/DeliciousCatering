/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vusieam.academics.deliciouscatering.domain.controllers;

import com.google.gson.Gson;
import com.vusieam.academics.deliciouscatering.data.ClientsDao;
import com.vusieam.academics.deliciouscatering.domain.models.BookingPaymentModel;
import com.vusieam.academics.deliciouscatering.domain.models.ClientDetails;
import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.FullBookingModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;

/**
 *
 * @author vusi
 */
@WebServlet(name = "manageBookings", urlPatterns = {"/manageBookings"})
public class ManageBookingsController extends HttpServlet {

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
        try {

            HttpSession session = request.getSession(false);
            if (session == null) {
                GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                paymentResponse.setStatus(false);
                paymentResponse.setCode(403);
                paymentResponse.setMessage("Session may have expired, please login.");
            } else {
                if (session.getAttribute("clientProfile") == null) {
                    GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                    paymentResponse.setStatus(false);
                    paymentResponse.setCode(403);
                    paymentResponse.setMessage("Session may have expired, please login.");
                } else {
                    String userJson = (String) session.getAttribute("clientProfile");
                    ClientModel client = new Gson().fromJson(userJson, ClientModel.class);

                    if (client.getRole().equalsIgnoreCase("client")) {
                        var transactiontype = request.getParameter("transactionType");
                        if (transactiontype.equalsIgnoreCase("makePayment")) {
                            GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                            BookingPaymentModel data = new BookingPaymentModel();
                            data.setBookingId(Integer.parseInt(request.getParameter("txtBookingId")));
                            data.setPaymentDate(Date.valueOf(request.getParameter("txtPaymentDate")));
                            data.setPaymentAmount(Double.parseDouble(request.getParameter("txtPaymentAmount")));
                            data.setClientComments(request.getParameter("txtPaymentComments"));
                            paymentResponse = dao.makeBookingPayment(data);
                            String json = new Gson().toJson(paymentResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);
                        } else if (transactiontype.equalsIgnoreCase("getClientBookings")) {
                            GenericResponse<FullBookingModel> bookingsResponse = new GenericResponse<>();
                            bookingsResponse = dao.getBookingsByClient(client.getId());
                            String json = new Gson().toJson(bookingsResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);
                        } else if (transactiontype.equalsIgnoreCase("getAdminBookings")) {
                            GenericResponse<FullBookingModel> bookingsResponse = new GenericResponse<>();
                            bookingsResponse = dao.getAllBookings();
                            String json = new Gson().toJson(bookingsResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);
                        }
                    } else {
                        var transactiontype = request.getParameter("transactionType");
                        if (transactiontype.equalsIgnoreCase("getAdminBookings")) {
                            GenericResponse<FullBookingModel> bookingsResponse = new GenericResponse<>();
                            bookingsResponse = dao.getAllBookings();
                            String json = new Gson().toJson(bookingsResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);
                        } else {

                            GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                            paymentResponse.setStatus(true);
                            paymentResponse.setCode(403);
                            paymentResponse.setMessage("Incorrect Api call.");
                            String json = new Gson().toJson(paymentResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);

                        }
                    }
                }
            }

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
            if (session == null) {
                GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                paymentResponse.setStatus(false);
                paymentResponse.setCode(403);
                paymentResponse.setMessage("Session may have expired, please login.");
            } else {
                if (session.getAttribute("clientProfile") == null) {
                    GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                    paymentResponse.setStatus(false);
                    paymentResponse.setCode(403);
                    paymentResponse.setMessage("Session may have expired, please login.");
                } else {
                    String userJson = (String) session.getAttribute("clientProfile");
                    ClientModel client = new Gson().fromJson(userJson, ClientModel.class);

                    if (client.getRole().equalsIgnoreCase("client")) {
                        var transactiontype = request.getParameter("transactionType");
                        if (transactiontype.equalsIgnoreCase("makePayment")) {
                            GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                            BookingPaymentModel data = new BookingPaymentModel();
                            data.setBookingId(Integer.parseInt(request.getParameter("txtBookingId")));
                            data.setPaymentDate(Date.valueOf(request.getParameter("txtPaymentDate")));
                            data.setPaymentAmount(Double.parseDouble(request.getParameter("txtPaymentAmount")));
                            data.setClientComments(request.getParameter("txtPaymentComments"));
                            paymentResponse = dao.makeBookingPayment(data);

                            String json = new Gson().toJson(paymentResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);

                        } else if (transactiontype.equalsIgnoreCase("cancelBooking")) {
                            GenericResponse<Boolean> cancelBookingResponse = new GenericResponse<>();
                            var bookingId = Integer.parseInt(request.getParameter("txtBookingId"));
                            cancelBookingResponse = dao.cancelBooking(bookingId);
                            String json = new Gson().toJson(cancelBookingResponse);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(json);
                        }
                    } else {
                        GenericResponse<BookingPaymentModel> paymentResponse = new GenericResponse<>();
                        paymentResponse.setStatus(true);
                        paymentResponse.setCode(200);
                        paymentResponse.setMessage("Authenticated");

                    }

                }
            }

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
