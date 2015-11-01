/*
 * Created on : 10.10.2015
 * Author     : Miguel Santamaria
 * Goal       : This servlet allows the user to add or edit apps of his own.
 */
package ch.heigvd.amt.gary.controllers;

import ch.heigvd.amt.gary.services.AppsManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.ejb.EJB;
import javax.json.*;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppActionServlet extends HttpServlet {

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
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      try (PrintWriter out = response.getWriter()) {
         request.setAttribute("selectedHeaderElem", "apps");
         request.setAttribute("email", request.getSession().getAttribute("email"));
         
         // Here we have both GET and POST request's parameters, so we need to do the
         // operations in the processRequest method.
         // GET request's parameter. Indicate the action (edit or add) to apply to the app.
         String action = request.getParameter("action") == null ? 
                                       "null" : request.getParameter("action");
         // POST request's parameter. If will be used to know if the user just loaded the
         // page (null value) or if he clicked on the "Create"/"Edit" button.
         String formAction = request.getParameter("formAction") == null ? 
                                       "null" : request.getParameter("formAction");
         
         // Depending on the app's action, we need to operate differently.
         switch (action) {
            case "add":
               // If the user clicked on the "Create" button, we need to add the new app
               // in the databse.
               if (formAction.equals("create"))
               {
                  String name = request.getParameter("name");
                  
                  // Check if fields are correctly filled. If not, send an error to the view.
                  if (name.isEmpty())
                  {
                     request.setAttribute("error", "You must fill the name field.");
                  }
                  else
                  {
                     // Create the app and redirect back the user to the apps' list.
                     appsManager.createApp(name, request.getParameter("description"),
                                           UUID.randomUUID().toString(), 0, 
                                           request.getParameter("state").equals("Enabled"), 
                                             (long)request.getSession().getAttribute("id"));
                     response.sendRedirect("appslist");
                     break;
                  }
               }
               
               request.setAttribute("pageTitle", "Register new app");
               request.getRequestDispatcher("WEB-INF/views/appaction.jsp").forward(request, response);
               break;
            case "edit":
               // If the user clicked on the "Edit" button, we need to edit the new app
               // in the databse.
               if (formAction.equals("edit"))
               {
                  // Check id the request is an AJAX one, coming from the appslist page.
                  boolean isAnAjaxRequest = (request.getParameter("ajax") != null);

                  // If this is not an AJAX request, we want to edit all app's attributes,
                  // and then redirect back the client to the apps' list.
                  if (!isAnAjaxRequest)
                  {
                     String name = request.getParameter("name");

                     // Check if fields are correctly filled. If not, send an error to the view.
                     if (name.isEmpty())
                     {
                        request.setAttribute("error", "You must fill the name field.");
                     }
                     else
                     {
                        // Edit the app.
                        appsManager.editApp(Long.valueOf(request.getParameter("app")), 
                                             request.getParameter("name"), 
                                             request.getParameter("description"), 
                                             request.getParameter("state").equals("Enabled"));

                        response.sendRedirect("appslist");
                        break;
                     }
                  }
                  // If this IS an AJAX request, we only want to update the app's
                  // status, then build a succes JSON response, and send it back 
                  // to the AJAX requester.
                  else
                  {
                     // Edit the app's status.
                     appsManager.editAppStatus(Long.valueOf(request.getParameter("app")), 
                                               request.getParameter("state").equals("Enabled"));

                     // Create a JSON object which contains the success information.
                     JsonObject jsonResponse = Json.createObjectBuilder()
                                                   .add("success", true)
                                                   .build();
                     // Send back the JSON response to the requester (in appslist.jsp).
                     out.println(jsonResponse.toString());
                     break;
                  }
               }
               // If there is no action indicated for the app, we simply redirect back the user to the
               // apps' list.
               else if (request.getParameter("app") == null || request.getParameter("app").equals(""))
               {
                  response.sendRedirect("appslist");
                  break;
               }
               
               // If the user just accessed to the edit page, we load the concerned app's details.
               Object app = appsManager.getApp(Long.valueOf(request.getParameter("app")));
               
               request.setAttribute("app", app);
               request.setAttribute("pageTitle", "App details");
               request.getRequestDispatcher("WEB-INF/views/appaction.jsp").forward(request, response);
               break;
            default:
               response.sendRedirect("appslist");
               break;
         }
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
      return "Allows the user to add or edit apps of his own.";
   }// </editor-fold>

}
