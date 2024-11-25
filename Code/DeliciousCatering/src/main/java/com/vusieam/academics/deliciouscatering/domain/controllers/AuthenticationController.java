package com.vusieam.academics.deliciouscatering.domain.controllers;

import com.google.gson.Gson;
import com.vusieam.academics.deliciouscatering.data.ClientsDao;
import com.vusieam.academics.deliciouscatering.domain.models.ClientModel;
import com.vusieam.academics.deliciouscatering.domain.models.GenericResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author vusi
 */
public class AuthenticationController extends HttpServlet {

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

        GenericResponse<ClientModel> authenticated = new GenericResponse<>();
        HttpSession session = request.getSession(false);
        if (session == null) {
            authenticated.setStatus(false);
            authenticated.setCode(403);
            authenticated.setMessage("Session may have expired, please login.");
        } else {
            if (session.getAttribute("clientProfile") == null) {
                authenticated.setStatus(false);
                authenticated.setCode(403);
                authenticated.setMessage("Session may have expired, please login.");
            } else {
                String userJson = (String) session.getAttribute("clientProfile");
                ClientModel client = new Gson().fromJson(userJson, ClientModel.class);
                authenticated.setStatus(true);
                authenticated.setCode(200);
                authenticated.setMessage("Authenticated");
                authenticated.setData(client);
            }
        }

        String json = new Gson().toJson(authenticated);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String accessType = request.getParameter("txtAccessType");

        //Client login
        if (accessType.equalsIgnoreCase("7B6D67CA-D13B-4A21-A1D9-305183C2723B")) {
            var authResponse = dao.clientAuthAsync(username, password);
            if (authResponse.getStatus()) {
                authResponse.getData().setRole("Client");
                String sessionUserJson = new Gson().toJson(authResponse.getData());
                HttpSession session = request.getSession();
                session.setAttribute("clientProfile", sessionUserJson);
                session.setAttribute("role", "client");
            }
            String json = new Gson().toJson(authResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } 
        else if (accessType.equalsIgnoreCase("5B90DCF2-80CF-4C4F-A5B3-0F6C533699B0")) {
            //Administrator login
            var authResponse = dao.adminAuthAsync(username, password);
            if (authResponse.getStatus()) {
                authResponse.getData().setRole("Admin");
                String sessionUserJson = new Gson().toJson(authResponse.getData());
                HttpSession session = request.getSession();
                session.setAttribute("clientProfile", sessionUserJson);
                session.setAttribute("role", "admin");
            }
            String json = new Gson().toJson(authResponse);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } 
        else {
            GenericResponse<ClientModel> authResponse = new GenericResponse<>();
            authResponse.setCode(403);
            authResponse.setStatus(false);
            authResponse.setMessage("Login failed. incorrect access type");
            
            String json = new Gson().toJson(authResponse);
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
    }
// </editor-fold>

}
