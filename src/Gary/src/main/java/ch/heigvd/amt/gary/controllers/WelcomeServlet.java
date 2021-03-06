package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.AppsManagerLocal;
import ch.heigvd.amt.gary.services.LoginServiceLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Author     : Jan Purro
 * Goal       : This servlet simply redirects the request towards the welcome
                page.
 */

@WebServlet(name = "WelcomeServlet", urlPatterns = {"/welcome"})
public class WelcomeServlet extends HttpServlet
{
   @EJB
   LoginServiceLocal loginService;
   
   @EJB
   AppsManagerLocal appsManager;
   
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
            throws ServletException, IOException
    {
        request.setAttribute("totalOfAccounts", loginService.countTotalAccounts());
        request.setAttribute("totalOfApps", appsManager.countTotalApps());
        request.setAttribute("numberOfUsersCreatedByApplicationsDuringThe30LastDays", 0);
        request.setAttribute("pageTitle", "Welcome");
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
    }

    /**
     * Redirects towards the welcome page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
