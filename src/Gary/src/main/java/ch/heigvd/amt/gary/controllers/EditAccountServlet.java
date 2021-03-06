package ch.heigvd.amt.gary.controllers;

import static ch.heigvd.amt.gary.controllers.RegistrationServlet.ATT_ERRORS;
import static ch.heigvd.amt.gary.controllers.RegistrationServlet.ATT_RESULT;
import ch.heigvd.amt.gary.services.LoginServiceLocal;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditAccountServlet", urlPatterns = {"/EditAccountServlet"})
public class EditAccountServlet extends HttpServlet {
    
    @EJB
    LoginServiceLocal loginService;
    
    public static final String EDITACCOUNT_VIEW = "/WEB-INF/views/editaccount.jsp";
    public static final String FIELD_PWD = "password";
    public static final String FIELD_NAMES = "names";
   
    /**
     * 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("selectedHeaderElem", "account");
        request.setAttribute("pageTitle", "Edit account");
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/editaccount.jsp").forward(request, response);
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
        
        
        String result;
        Map<String, String> errors = new HashMap<>();
        
        // The parameters sent by the user
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String action = request.getParameter("action"); 
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        
        if(action.equals("editaccount"))
        {
            // Verify if the firstname and the lastname are valids
            try {
                loginService.firstnameAndLastnameValidation(firstname, lastname);        
            } catch (Exception e) {
                errors.put(FIELD_NAMES, e.getMessage());
            }
            // Verify if the password and the password confirmations are valids
            try {
                loginService.passwordValidation(password, passwordConfirmation);         
            } catch (Exception e) {
                errors.put(FIELD_PWD, e.getMessage());
            }

            // If there is no error
            if(errors.isEmpty())
            {
                // Get the user's id
                long id = (long)request.getSession().getAttribute("id");
                // The account is eddited
                loginService.editAccount(id, action, action, password);
                // Tell the user that 
                result = "Modifications saved";
            }
            else
            {
                // Tell the user that there is something wrong
                result = "Modifications ignored";
            }   

            request.setAttribute(ATT_ERRORS, errors);
            request.setAttribute(ATT_RESULT, result);

            this.getServletContext().getRequestDispatcher(EDITACCOUNT_VIEW)
                            .forward(request, response);           
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
