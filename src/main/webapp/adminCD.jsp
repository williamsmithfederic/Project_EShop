<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Administration des CD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    
    <style>
        .search-admin-box {
            background: white;
            border-radius: 10px;
            padding: 15px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
    </style>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">
                <i class="bi bi-tools"></i> Gestion des CD & DVD
            </h2>
            <a href="CDAdminServlet?action=new" class="btn btn-success">
                <i class="bi bi-plus-circle"></i> Ajouter un CD & DVD
            </a>
        </div>

        <!-- ✅ BARRE DE RECHERCHE SIMPLE -->
        <div class="search-admin-box">
            <div class="input-group">
                <span class="input-group-text bg-primary text-white">
                    <i class="bi bi-search"></i>
                </span>
                <input type="text" 
                       id="adminSearchInput" 
                       class="form-control" 
                       placeholder="Rechercher par album, artiste ou pays..."
                       onkeyup="filterAdminTable()">
            </div>
        </div>

        <c:if test="${not empty listCD}">
            <div class="card shadow-sm">
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped mb-0" id="adminTable">
                            <thead class="table-primary">
                                <tr>
                                    <th>ID</th>
                                    <th>Album</th>
                                    <th>Artiste</th>
                                    <th>Pays</th>
                                    <th>Prix</th>
                                    <th class="text-center">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cd" items="${listCD}">
                                    <tr class="admin-row" 
                                        data-album="${cd.album}" 
                                        data-artist="${cd.artist}" 
                                        data-country="${cd.country}">
                                        <td>${cd.id}</td>
                                        <td><strong>${cd.album}</strong></td>
                                        <td>${cd.artist}</td>
                                        <td>${cd.country}</td>
                                        <td>
                                            <fmt:formatNumber value="${cd.price}" type="currency" currencySymbol="$" />
                                        </td>
                                        <td class="text-center">
                                            <a href="CDAdminServlet?action=edit&id=${cd.id}" 
                                               class="btn btn-sm btn-warning me-2">
                                                <i class="bi bi-pencil"></i> Modifier
                                            </a>
                                            <a href="CDAdminServlet?action=delete&id=${cd.id}" 
                                               class="btn btn-sm btn-danger"
                                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce CD ?');">
                                                <i class="bi bi-trash"></i> Supprimer
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <!-- Message "Aucun résultat" -->
            <div id="noResultsAdmin" class="alert alert-info text-center mt-3" style="display: none;">
                <i class="bi bi-emoji-frown"></i> Aucun CD ne correspond à votre recherche.
            </div>
        </c:if>

        <c:if test="${empty listCD}">
            <div class="alert alert-info text-center">
                <i class="bi bi-info-circle"></i> Aucun CD dans la base de données.
            </div>
        </c:if>

        <div class="mt-3">
            <a class="btn btn-secondary">
                <i></i> Fin liste 
            </a>
        </div>
    </div>

    <!--  SCRIPT DE RECHERCHE SIMPLE -->
    <script>
        function filterAdminTable() {
            const searchValue = document.getElementById('adminSearchInput').value.toLowerCase();
            const rows = document.querySelectorAll('.admin-row');
            const noResults = document.getElementById('noResultsAdmin');
            
            let visibleCount = 0;
            
            rows.forEach(row => {
                const album = row.getAttribute('data-album').toLowerCase();
                const artist = row.getAttribute('data-artist').toLowerCase();
                const country = row.getAttribute('data-country').toLowerCase();
                
                if (album.includes(searchValue) || 
                    artist.includes(searchValue) || 
                    country.includes(searchValue)) {
                    row.style.display = '';
                    visibleCount++;
                } else {
                    row.style.display = 'none';
                }
            });
            
            // Afficher/cacher le message "Aucun résultat"
            if (visibleCount === 0 && searchValue !== '') {
                noResults.style.display = 'block';
            } else {
                noResults.style.display = 'none';
            }
        }
    </script>
</body>
</html>