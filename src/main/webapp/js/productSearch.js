/**
 * Script de recherche de produits pour le formulaire d'administration
 * Charge les VRAIS produits depuis le catalogue de la base de données
 */

// Variable globale pour stocker les produits
let products = [];

// Charger les produits au chargement de la page
document.addEventListener('DOMContentLoaded', function() {
    loadProducts();
    console.log('Produits chargés:', products.length, 'articles');
});

// Fonction pour charger les produits depuis la variable injectée par JSP
function loadProducts() {
    // Les produits sont injectés directement depuis le JSP via la variable catalogueProducts
    if (typeof catalogueProducts !== 'undefined') {
        products = catalogueProducts;
        console.log('✅ Produits réels chargés depuis la base de données:', products);
    } else {
        console.warn('⚠️ Aucun produit trouvé. Vérifiez que catalogueProducts est défini dans le JSP.');
        products = [];
    }
}

// Fonction de recherche appelée à chaque frappe
function searchProducts() {
    const searchValue = document.getElementById('productSearch').value.toLowerCase().trim();
    const suggestionsContainer = document.getElementById('productSuggestions');
    
    // Si la recherche est vide, cacher les suggestions
    if (searchValue === '') {
        suggestionsContainer.style.display = 'none';
        suggestionsContainer.innerHTML = '';
        return;
    }
    
    // Si aucun produit n'est chargé
    if (products.length === 0) {
        suggestionsContainer.style.display = 'block';
        suggestionsContainer.innerHTML = `
            <div class="product-item text-center text-warning">
                <i class="bi bi-exclamation-triangle"></i> Aucun produit disponible dans le catalogue
            </div>
        `;
        return;
    }
    
    // Filtrer les produits correspondants
    const filteredProducts = products.filter(product => {
        const album = product.album ? product.album.toLowerCase() : '';
        const artist = product.artist ? product.artist.toLowerCase() : '';
        const country = product.country ? product.country.toLowerCase() : '';
        
        return album.includes(searchValue) ||
               artist.includes(searchValue) ||
               country.includes(searchValue);
    });
    
    // Afficher les résultats
    if (filteredProducts.length > 0) {
        suggestionsContainer.style.display = 'block';
        suggestionsContainer.innerHTML = '';
        
        // Limiter à 10 résultats pour la performance
        const limitedResults = filteredProducts.slice(0, 10);
        
        limitedResults.forEach(product => {
            const productItem = createProductItem(product);
            suggestionsContainer.appendChild(productItem);
        });
        
        // Ajouter un message si plus de 10 résultats
        if (filteredProducts.length > 10) {
            const moreDiv = document.createElement('div');
            moreDiv.className = 'product-item text-center text-muted';
            moreDiv.style.fontStyle = 'italic';
            moreDiv.innerHTML = `
                <small>
                    <i class="bi bi-info-circle"></i> 
                    +${filteredProducts.length - 10} autres résultats. Affinez votre recherche.
                </small>
            `;
            suggestionsContainer.appendChild(moreDiv);
        }
        
    } else {
        suggestionsContainer.style.display = 'block';
        suggestionsContainer.innerHTML = `
            <div class="product-item text-center text-muted">
                <i class="bi bi-emoji-frown"></i> Aucun produit trouvé pour "${searchValue}"
            </div>
        `;
    }
}

// Créer un élément HTML pour chaque produit
function createProductItem(product) {
    const div = document.createElement('div');
    div.className = 'product-item';
    div.onclick = () => selectProduct(product);
    
    // Formater le prix
    const formattedPrice = typeof product.price === 'number' 
        ? product.price.toFixed(2) 
        : parseFloat(product.price).toFixed(2);
    
    div.innerHTML = `
        <div class="d-flex justify-content-between align-items-center">
            <div style="flex: 1;">
                <strong class="text-primary">
                    <i class="bi bi-disc"></i> ${escapeHtml(product.album)}
                </strong>
                <br>
                <small class="text-muted">
                    <i class="bi bi-person"></i> ${escapeHtml(product.artist)} | 
                    <i class="bi bi-globe"></i> ${escapeHtml(product.country)}
                </small>
            </div>
            <div>
                <span class="badge bg-success">$${formattedPrice}</span>
            </div>
        </div>
    `;
    
    // Effet hover
    div.addEventListener('mouseenter', function() {
        this.style.backgroundColor = '#e7f1ff';
    });
    
    div.addEventListener('mouseleave', function() {
        this.style.backgroundColor = '';
    });
    
    return div;
}

// Fonction pour sélectionner un produit et remplir le formulaire
function selectProduct(product) {
    console.log('Produit sélectionné:', product);
    
    // Remplir les champs du formulaire
    document.getElementById('album').value = product.album || '';
    document.getElementById('artist').value = product.artist || '';
    document.getElementById('country').value = product.country || '';
    document.getElementById('price').value = product.price || '';
    
    // Cacher les suggestions
    document.getElementById('productSuggestions').style.display = 'none';
    document.getElementById('productSearch').value = '';
    
    // Effet visuel de confirmation sur tous les champs
    const fields = ['album', 'artist', 'country', 'price'];
    fields.forEach(fieldId => {
        const field = document.getElementById(fieldId);
        field.classList.add('border-success', 'border-2');
    });
    
    // Focus sur le premier champ
    document.getElementById('album').focus();
    
    // Retirer l'effet après 2 secondes
    setTimeout(() => {
        fields.forEach(fieldId => {
            const field = document.getElementById(fieldId);
            field.classList.remove('border-success', 'border-2');
        });
    }, 2000);
    
    // Message de succès
    showSuccessMessage(`Produit "${product.album}" sélectionné ! Modifiez les champs si nécessaire avant d'enregistrer.`);
}

// Afficher un message de succès
function showSuccessMessage(message) {
    // Supprimer l'ancien message s'il existe
    const oldAlert = document.querySelector('.alert-product-selected');
    if (oldAlert) {
        oldAlert.remove();
    }
    
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-success alert-dismissible fade show mt-3 alert-product-selected';
    alertDiv.innerHTML = `
        <i class="bi bi-check-circle-fill"></i> <strong>Succès !</strong> ${escapeHtml(message)}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;
    
    const form = document.querySelector('form');
    form.parentNode.insertBefore(alertDiv, form);
    
    // Supprimer automatiquement après 5 secondes
    setTimeout(() => {
        if (alertDiv && alertDiv.parentNode) {
            alertDiv.classList.remove('show');
            setTimeout(() => alertDiv.remove(), 150);
        }
    }, 5000);
}

// Fonction pour échapper le HTML (sécurité)
function escapeHtml(text) {
    if (!text) return '';
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.toString().replace(/[&<>"']/g, m => map[m]);
}

// Fermer les suggestions si on clique en dehors
document.addEventListener('click', function(event) {
    const searchBox = document.getElementById('productSearch');
    const suggestions = document.getElementById('productSuggestions');
    
    if (searchBox && suggestions) {
        if (!searchBox.contains(event.target) && !suggestions.contains(event.target)) {
            suggestions.style.display = 'none';
        }
    }
});

// Support du clavier (touches haut/bas pour naviguer, Enter pour sélectionner)
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('productSearch');
    
    if (searchInput) {
        let selectedIndex = -1;
        
        searchInput.addEventListener('keydown', function(e) {
            const suggestions = document.getElementById('productSuggestions');
            const items = suggestions.querySelectorAll('.product-item');
            
            if (items.length === 0) return;
            
            // Flèche vers le bas
            if (e.key === 'ArrowDown') {
                e.preventDefault();
                selectedIndex = (selectedIndex + 1) % items.length;
                highlightItem(items, selectedIndex);
            }
            
            // Flèche vers le haut
            if (e.key === 'ArrowUp') {
                e.preventDefault();
                selectedIndex = selectedIndex <= 0 ? items.length - 1 : selectedIndex - 1;
                highlightItem(items, selectedIndex);
            }
            
            // Touche Entrée
            if (e.key === 'Enter' && selectedIndex >= 0) {
                e.preventDefault();
                items[selectedIndex].click();
                selectedIndex = -1;
            }
            
            // Touche Échap
            if (e.key === 'Escape') {
                suggestions.style.display = 'none';
                selectedIndex = -1;
            }
        });
    }
});

// Mettre en surbrillance l'élément sélectionné au clavier
function highlightItem(items, index) {
    items.forEach((item, i) => {
        if (i === index) {
            item.style.backgroundColor = '#0d6efd';
            item.style.color = 'white';
            item.scrollIntoView({ block: 'nearest' });
        } else {
            item.style.backgroundColor = '';
            item.style.color = '';
        }
    });
}

console.log('Script de recherche de produits chargé avec succès');