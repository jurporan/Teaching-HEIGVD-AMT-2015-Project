package ch.heigvd.amt.gary.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter 
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(
                                  httpRequest.getContextPath().length());

        boolean isTargetUrlProtected = true;

        if (path.startsWith("/resources/"))
        {
            isTargetUrlProtected = false;
        }
        else if ("/".equals(path)) 
        {
            isTargetUrlProtected = false;
        }
        else if ("/welcome".equals(path)) 
        {
            isTargetUrlProtected = false;            
        }
        else if ("/login".equals(path)) 
        {
            isTargetUrlProtected = false;
        }
        else if ("/registration".equals(path)) 
        {
            isTargetUrlProtected = false;
        }

        if (httpRequest.getSession().getAttribute("email") == null && isTargetUrlProtected) 
        {
            request.setAttribute("pageTitle", "Login");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } 

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
