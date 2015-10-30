/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import javax.ejb.EJB;

/**
 *
 * @author lyuyhn
 */
@WebServlet(name = "testjpa", urlPatterns = {"/testjpa"})
public class testjpa extends HttpServlet {

    @EJB
    AccountDAO dao;
    
    @EJB
    AppDAO adao;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        Account a = dao.create("lol@coucou.com", "test", "java", "1234");
        Account x = dao.create("lol@coucou.com2", "test", "java", "1234");
        Account y = dao.create("lol@coucou.com3", "test", "java", "1234");
        Account b = dao.login("lol@coucou.comdd", "1234");
        if (b == null) {System.out.println("ZEROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");}
        
        System.out.println("EXISTEEEEEEEEEE??????????????? " + dao.exists("lol@coucou.com"));
        System.out.println("EXISTEEEEEEEEEE??????????????? " + dao.exists("proutout"));
        dao.count();

        adao.create("poney", "app", "dfghjk", 4, true, a);
        App lol = adao.get(1l);
        System.out.println("CAAAAAAAAAAAAAA FONCTIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNNEEEEEEEEEEEEEEEEEEEEEE " + lol.getName());
        
        Account ax = dao.login("lol@coucou.com", "1234");
        System.out.println("J'aime les PONEYS "  + ax.getFirstName());
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testjpa</title>");            
            out.println(a.getFirstName());
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testjpa at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
