/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.AppsManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Miguel
 */
@WebServlet(name = "ServletAppsList", urlPatterns = {"/appslist"})
public class ServletAppsList extends HttpServlet {

   @EJB(beanName = "AppsManager")
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
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
         Object apps = appsManager.getAllApps();
         
         request.setAttribute("pageTitle", "You apps");
         request.setAttribute("apps", apps);
         request.getRequestDispatcher("WEB-INF/views/appslist.jsp").forward(request, response);
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

/**
 * FEEDBACK:
 * - typo: Your apps (not You apps)
 * 
 * - in many applications, people prefer to use AccountServlet rather than ServletAccount.
 * 
 * - the commit message says that this page does not work. You probably know that it is because
 *   AppsDataStore.getAllApps() is not implemented and throws an exception. If you want my opinion, it would
 *   have been worthwhile to spend time (we are talking about 2 minutes) to return the list of apps from
 *   the session bean before making this commit (and in addition to make a link to this page from the
 *   default index page). This way, everybody in the team would have been able to get the update, build and
 *   run locally. Even if the functionality is very small, seeing progress on your machine and seeing step-by-step
 *   improvements has a very positive impact in order to get your on the same page, in the same rythm. It is really
 *   about psychology and coordination. In software engineering, people often talk about "baby steps": I do a small
 *   code change, I pull other devs modifs from remove git, I test, it works, I commit and I push. Try it and
 *   you will see that it put you in a very positive and productive mood.
 */