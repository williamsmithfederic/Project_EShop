/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/**
 * Script de recherche pour le catalogue de CD
 * Filtre les CD en temps réel par album, artiste ou pays
 */

function filterCDs() {
    // Récupère la valeur de recherche en minuscule
    const searchValue = document.getElementById('searchInput').value.toLowerCase();
    
    // Récupère toutes les cartes de CD
    const cdCards = document.querySelectorAll('.cd-card');
    
    let visibleCount = 0;
    
    // Parcourt chaque carte
    cdCards.forEach(card => {
        // Récupère les données de la carte
        const album = card.getAttribute('data-album').toLowerCase();
        const artist = card.getAttribute('data-artist').toLowerCase();
        const country = card.getAttribute('data-country').toLowerCase();
        
        // Vérifie si la recherche correspond à l'un des champs
        if (album.includes(searchValue) || 
            artist.includes(searchValue) || 
            country.includes(searchValue)) {
            card.style.display = 'block';
            visibleCount++;
        } else {
            card.style.display = 'none';
        }
    });
    
    // Affiche/cache le message "Aucun résultat"
    const noResults = document.getElementById('noResults');
    if (visibleCount === 0 && searchValue !== '') {
        noResults.style.display = 'block';
    } else {
        noResults.style.display = 'none';
    }
}