/*
 * Author     : Jan Purro
 * Goal       : This filter prevents the users to access some page depending if
                they are logged in or not.
 */
package ch.heigvd.amt.gary.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter 
{
    /**
    * Filter all requests. If the user is not logged in and tries to access to 
    * a protected page he's redirected to the login page.
    * If the user tries to access to the login, welcome or registration page 
    * when he is logged in he is redirected towards his applications list page.
    * In the other cases the user can access the pages normally.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException
    {
        // We cast the request into an httpRequest and extract the path.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(
                                  httpRequest.getContextPath().length());
        // We cast the response into an httpResponse to be able to redirect it
        // to another URL.
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        /* For the the protected pages we apply a whiteliste policy i.e. all
           pages are protected except some specific pages.
           For the pages accessivle to a logges user we apply a blacklist policy.
           i.e. all pages are accessible, except some specific ones.
        */
        boolean isTargetUrlProtected = true;
        boolean isAccessibleToLoggedUser = true;

        if (path.startsWith("/resources/"))
        {
            isTargetUrlProtected = false;
        }
        else if ("/".equals(path)) 
        {
            isTargetUrlProtected = false;
            isAccessibleToLoggedUser = false;
        }
        else if ("/welcome".equals(path)) 
        {
            isTargetUrlProtected = false;
            isAccessibleToLoggedUser = false;
        }
        else if ("/generatetestdata".equals(path)) 
        {
            isTargetUrlProtected = false;
        }
        else if ("/login".equals(path)) 
        {
            isTargetUrlProtected = false;
            isAccessibleToLoggedUser = false;
        }
        else if ("/registration".equals(path)) 
        {
            isTargetUrlProtected = false;
            isAccessibleToLoggedUser = false;
        }
        
        // If the user is not logged in and the url is protected we redirect him
        // to the login page.
        if (httpRequest.getSession().getAttribute("email") == null && isTargetUrlProtected) 
        {
            request.setAttribute("pageTitle", "Login");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } 

        // If the player is logged in and tries to access to some blacklisted pages we redirect him
        // to his appslist.
        else if (httpRequest.getSession().getAttribute("email") != null && !isAccessibleToLoggedUser)
        {
            httpResponse.sendRedirect("appslist");
        }
        
        // Otherwise we just forward the request.
        else
        {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
