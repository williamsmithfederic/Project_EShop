package modele;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CD> items = new ArrayList<>();

    /** Ajoute un CD au panier (incrémente si déjà présent) */
    public void addItem(CD cd) {
        for (CD item : items) {
            if (item.getAlbum().equals(cd.getAlbum())) {
                item.setQuantity(item.getQuantity() + cd.getQuantity());
                return;
            }
        }
        items.add(cd);
    }

    /** Retourne la liste des CD dans le panier */
    public List<CD> getItems() {
        return items;
    }

    /** Retourne le total du panier */
    public float getTotal() {
        float total = 0;
        for (CD cd : items) {
            total += cd.getPrice() * cd.getQuantity();
        }
        return total;
    }

    /** Supprime un CD du panier */
    public void supprimerCD(String album) {
        items.removeIf(cd -> cd.getAlbum().equals(album));
    }

   
    public void diminuerQuantite(String album) {
        for (CD cd : items) {
            if (cd.getAlbum().equals(album)) {
                if (cd.getQuantity() > 1)
                    cd.setQuantity(cd.getQuantity() - 1);
                else
                    items.remove(cd);
                return;
            }
        }
    }

    /** Augmente la quantité d’un CD */
    public void augmenterQuantite(String album) {
        for (CD cd : items) {
            if (cd.getAlbum().equals(album)) {
                cd.setQuantity(cd.getQuantity() + 1);
                return;
            }
        }
    }

    /** Vide le panier */
    public void clear() {
        items.clear();
    }
}
