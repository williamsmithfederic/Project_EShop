package controleur;

import java.io.IOException;

import java.util.List;

import java.util.Vector;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import modele.*;

/**
 *
 * Servlet d'initialisation du catalogue de CD CrÃ©e une liste de CD et la place
 *
 * dans la session utilisateur. Redirige ensuite vers la page EShop.jsp.
 *
 *
 *
 * @author ChatGPT
 *
 */
@WebServlet(name = "InitCatalogue", urlPatterns = {"/InitCatalogue"})

public class InitCatalogue extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("catalogue") == null) {

            CDDAO dao = new CDDAO();

            List<CD> catalogue = dao.getAll();

            session.setAttribute("catalogue", catalogue);

        }

        response.sendRedirect("EShop.jsp");

    }

}
