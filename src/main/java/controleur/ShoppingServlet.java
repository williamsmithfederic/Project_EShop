package controleur;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modele.CD;
import modele.Cart;

@WebServlet(name = "ShoppingServlet", urlPatterns = {"/ShoppingServlet"})
public class ShoppingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart panier = (Cart) session.getAttribute("panier");

        if (panier == null) {
            panier = new Cart();
            session.setAttribute("panier", panier);
        }

        if ("add".equals(action)) {
            String titre = request.getParameter("titre");
            String artiste = request.getParameter("artiste");
            float prix = Float.parseFloat(request.getParameter("prix"));

            CD cd = new CD(titre, artiste, prix);
            panier.addItem(cd);
        }

        response.sendRedirect("EShop.jsp");
    }
}
