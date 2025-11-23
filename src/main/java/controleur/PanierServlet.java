package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Cart;

@WebServlet(name = "PanierServlet", urlPatterns = {"/PanierServlet"})
public class PanierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart panier = (Cart) session.getAttribute("panier");

        if (panier == null) {
            panier = new Cart();
            session.setAttribute("panier", panier);
        }

        String action = request.getParameter("action");
        String album = request.getParameter("album");

        if (action != null && album != null) {
            switch (action) {
                case "supprimer":
                    panier.supprimerCD(album);
                    break;
                case "moins":
                    panier.diminuerQuantite(album);
                    break;
                case "plus":
                    panier.augmenterQuantite(album);
                    break;
            }
        }

        request.getRequestDispatcher("/panier.jsp").forward(request, response);
    }
}
