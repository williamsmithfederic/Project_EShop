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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.CD;
import modele.Cart;

/**
 * Servlet pour générer une facture PDF du contenu du panier.
 */
public class DetailPanierPdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            HttpSession session = request.getSession();
            Cart panier = (Cart) session.getAttribute("panier");

            if (panier == null || panier.getItems().isEmpty()) {
                document.add(new Paragraph("Votre panier est vide",
                        FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.RED)));
            } else {
                Paragraph titre = new Paragraph("Facture CDShop",
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD, BaseColor.BLUE));
                titre.setAlignment(Element.ALIGN_CENTER);
                titre.setSpacingAfter(20f);
                document.add(titre);

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // En-têtes de colonnes
                table.addCell(new Paragraph("Album", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                table.addCell(new Paragraph("Artiste", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                table.addCell(new Paragraph("Prix ($)", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                table.addCell(new Paragraph("Quantité", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));

                // Lignes du panier
                List<CD> items = panier.getItems();
                for (CD cd : items) {
                    table.addCell(cd.getAlbum());
                    table.addCell(cd.getArtist());
                    table.addCell(String.format("%.2f", cd.getPrice()));
                    table.addCell(String.valueOf(cd.getQuantity()));
                }

                document.add(table);

                Paragraph total = new Paragraph("Total : " + String.format("%.2f $", panier.getTotal()),
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK));
                total.setAlignment(Element.ALIGN_RIGHT);
                document.add(total);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Génération du PDF du panier CDShop";
    }
}
