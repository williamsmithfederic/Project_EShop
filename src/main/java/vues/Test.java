/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vues;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.CD;


/**
 * Cette servlet contrôleur prend en charge l'édition du détail du contenu du
 * panier et de la facture au format PDF.
 *
 * @author Philippe.Genoud@imag.fr
 */
public class Test extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            HttpSession session = request.getSession();
          Vector panier = (Vector)session.getAttribute("shoppingcart");
            if (panier == null) // il n'y a pas de paniers
            {
                document.add(new Paragraph("Votre panier est vide"));

            } else {
                Paragraph titre = new Paragraph("Facture", FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLDITALIC, BaseColor.BLUE));
                titre.setAlignment(Element.ALIGN_CENTER);
                titre.setSpacingAfter(30f);

                document.add(titre);

                int prixTotal = 0;
                PdfPTable table = new PdfPTable(3);

                table.addCell(new Paragraph("Produit", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                table.addCell(new Paragraph("Prix unitaire HT", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));
                table.addCell(new Paragraph("Quantité", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC, BaseColor.BLACK)));

                for (int index=0; index < panier.size();index++)
                {
                   CD a = (CD) panier.elementAt(index);
                   table.addCell(" " + a.getAlbum() + " ");
                   table.addCell(" " + a.getPrice() + " $");
                   table.addCell(" " + a.getQuantity());
                }
               document.add(table);
                /*Paragraph paraPrixTotal = new Paragraph("Prix total HT : " + panier.getPrixTotalHT() + " Euros");
                paraPrixTotal.setSpacingBefore(20f);
                document.add(paraPrixTotal);
                Paragraph paraPrixTotalTTC = new Paragraph("Prix total TTC : " + panier.getPrixTotalTTC() + " Euros");
                paraPrixTotalTTC.setSpacingBefore(20f);
                document.add(paraPrixTotalTTC);*/
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
