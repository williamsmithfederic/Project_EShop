package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String VALID_USERNAME_USER = "user";
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ((VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)))  {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("isLoggedIn", true);
            response.sendRedirect("CDAdminServlet");
        } else {
            
                    if ((VALID_USERNAME_USER.equals(username) && VALID_PASSWORD.equals(password)))  {

                        HttpSession session = request.getSession();
                        session.setAttribute("user", username);
                        session.setAttribute("isLoggedIn", true);
                        response.sendRedirect("InitCatalogue");
            
            
            } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}