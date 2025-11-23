<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- ✅ VÉRIFICATION DE CONNEXION -->
<c:if test="${sessionScope.isLoggedIn != true}">
    <c:redirect url="login.jsp" />
</c:if>

<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <title>CDShop - Catalogue</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

        <style>
            body {
                background-image: url('image/cd7.png');
                background-size: cover;
                background-position: center;
                background-attachment: fixed;
                background-repeat: no-repeat;
            }

            .search-box {
                background: white;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                margin-bottom: 30px;
            }

            .btn-primary {
                background-color: #ff8040 !important;
                border-color: #28a745 !important;
            }

            .btn-primary:hover {
                background-color: #0d6efd !important;
                border-color: #1e7e34 !important;
            }
        </style>

    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <h2 class="text-center mb-4 text-primary">
                <i class="bi bi-disc"></i> Nos CD disponibles
            </h2>

            <c:if test="${sessionScope.catalogue == null}">
                <jsp:forward page="/InitCatalogue" />
            </c:if>

            <!-- ✅ ZONE DE RECHERCHE -->
            <div class="search-box">
                <div class="input-group input-group-lg">
                    <span class="input-group-text bg-primary text-white">
                        <i class="bi bi-search"></i>
                    </span>
                    <input type="text" 
                           id="searchInput" 
                           class="form-control" 
                           placeholder="Rechercher par album, artiste ou pays..."
                           onkeyup="filterCDs()">
                </div>
               
            </div>

            <c:if test="${empty sessionScope.catalogue}">
                <div class="alert alert-warning text-center">
                    Aucun CD disponible pour le moment.
                </div>
            </c:if>

            <!-- ✅ Message "Aucun résultat" (caché par défaut) -->
            <div id="noResults" class="alert alert-info text-center" style="display: none;">
                <i class="bi bi-emoji-frown"></i> Aucun CD ne correspond à votre recherche.
            </div>

            <div class="row" id="cdContainer">
                <c:forEach var="cd" items="${sessionScope.catalogue}">
                    <div class="col-md-4 mb-4 cd-card" 
                         data-album="${cd.album}" 
                         data-artist="${cd.artist}" 
                         data-country="${cd.country}">
                        <div class="card shadow-sm border-0 rounded-4">
                            <div class="card-body">
                                <h5 class="card-title text-primary">${cd.album}</h5>
                                <p class="card-text">
                                    <strong>Artiste :</strong> ${cd.artist}<br>
                                    <strong>Pays :</strong> ${cd.country}<br>
                                    <strong>Prix :</strong> 
                                    <fmt:formatNumber value="${cd.price}" type="currency" currencySymbol="$" />
                                </p>
                                <form action="AddToCart" method="post">
                                    <input type="hidden" name="album" value="${cd.album}" />
                                    <input type="hidden" name="artist" value="${cd.artist}" />
                                    <input type="hidden" name="country" value="${cd.country}" />
                                    <input type="hidden" name="price" value="${cd.price}" />
                                    <button type="submit" class="btn btn-primary w-100 rounded-pill shadow-sm">
                                        <i class="bi bi-cart-plus"></i> Ajouter au panier
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        

        <!-- ✅ SCRIPT DE RECHERCHE EXTERNE -->
        <script src="./js/search.js"></script>

    </body>
</html>