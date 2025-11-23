package controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.CD;
import modele.CDDAO;
import java.util.List;

@WebServlet(name = "CDAdminServlet", urlPatterns = {"/CDAdminServlet"})
public class CDAdminServlet extends HttpServlet {

    private CDDAO cdDAO = new CDDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listCD(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCD(request, response);
                break;
            case "new":
                showNewForm(request, response);
                break;
            default:
                listCD(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("insert".equals(action)) {
            insertCD(request, response);
        } else if ("update".equals(action)) {
            updateCD(request, response);
        }
    }

    // Afficher la liste des CD
    private void listCD(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CD> listCD = cdDAO.getAll();
        request.setAttribute("listCD", listCD);
        request.getRequestDispatcher("adminCD.jsp").forward(request, response);
    }

    // Afficher le formulaire d'ajout
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("adminCDForm.jsp").forward(request, response);
    }

    // Afficher le formulaire de modification
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CD existingCD = cdDAO.getById(id);
        request.setAttribute("cd", existingCD);
        request.getRequestDispatcher("adminCDForm.jsp").forward(request, response);
    }

    // Insérer un nouveau CD
    private void insertCD(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String album = request.getParameter("album");
        String artist = request.getParameter("artist");
        String country = request.getParameter("country");
        float price = Float.parseFloat(request.getParameter("price"));
        
        CD newCD = new CD(album, artist, country, price, 1);
        cdDAO.addCD(newCD);
        
        // ✅ RAFRAÎCHIR LE CATALOGUE DANS LA SESSION
        refreshCatalogue(request);
        
        response.sendRedirect("CDAdminServlet?action=list");
    }

    // Mettre à jour un CD existant
    private void updateCD(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String album = request.getParameter("album");
        String artist = request.getParameter("artist");
        String country = request.getParameter("country");
        float price = Float.parseFloat(request.getParameter("price"));
        
        CD cd = new CD(album, artist, country, price, 1);
        cd.setId(id);
        cdDAO.updateCD(cd);
        
        // ✅ RAFRAÎCHIR LE CATALOGUE DANS LA SESSION
        refreshCatalogue(request);
        
        response.sendRedirect("CDAdminServlet?action=list");
    }

    // Supprimer un CD
    private void deleteCD(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cdDAO.deleteCD(id);
        
        // ✅ RAFRAÎCHIR LE CATALOGUE DANS LA SESSION
        refreshCatalogue(request);
        
        response.sendRedirect("CDAdminServlet?action=list");
    }
    
    // ✅ MÉTHODE POUR RAFRAÎCHIR LE CATALOGUE
    private void refreshCatalogue(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<CD> catalogue = cdDAO.getAll();
        session.setAttribute("catalogue", catalogue);
    }
}