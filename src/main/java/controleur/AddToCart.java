package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.CD;
import modele.Cart;

/**
 * Servlet qui gère l'ajout d'un CD au panier de l'utilisateur.
 
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        //  Récupère et crée le panier dans la session
        Cart panier = (Cart) session.getAttribute("panier");
        if (panier == null) {
            panier = new Cart();
            session.setAttribute("panier", panier);
        }

        // Récupère les infos envoyées depuis le formulaire JSP
        String album = request.getParameter("album");
        String artist = request.getParameter("artist");
        String country = request.getParameter("country");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = 1;

        // Crée un nouveau CD
        CD cd = new CD(album, artist, country, price, quantity);

        // Ajoute ou met à jour l'article dans le panier
        panier.addItem(cd);

        // Sauvegarde dans la session 
        session.setAttribute("panier", panier);

        // Redirige vers la page panier pour voir les articles
        response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("EShop.jsp");
    }
}
