<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header_user.jsp" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Validation de la commande</title>

        <!--  Liens Bootstrap et icônes -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

        <style>
            body {
                background-color: #f8f9fa;
            }
            .navbar {
                background-color: #007bff;
            }
            .navbar a {
                color: white !important;
                font-weight: 500;
            }
            footer {
                background-color: #007bff;
                color: white;
                padding: 10px;
                text-align: center;
            }
            .btn-success {
                background-color: #198754;
                border: none;
            }
        </style>
    </head>

    <body>

        <!--  Barre de navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand fw-bold" href="index.jsp">
                    <i class="bi bi-disc"></i> CDShop
                </a>

            </div>
        </nav>

        <div class="container mt-5">
            <h3 class="text-primary mb-4 text-center">
                <i class="bi bi-credit-card"></i> Validation de la commande
            </h3>

            <c:choose>
                <c:when test="${not empty sessionScope.panier and not empty sessionScope.panier.items}">
                    <div class="card shadow-sm border-0 rounded-4 p-4">
                        <h5 class="h5 text-secondary mb-3">Résumé du panier</h5>

                        <ul class="list-group mb-3">
                            <c:forEach var="cd" items="${sessionScope.panier.items}">
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    ${cd.album} — ${cd.artist}
                                    <span>
                                        <fmt:formatNumber value="${cd.price}" type="currency" currencySymbol="$"/>
                                        × ${cd.quantity}
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>

                        <div class="text-end fw-bold mb-3">
                            Total :
                            <fmt:formatNumber value="${sessionScope.panier.total}" type="currency" currencySymbol="$"/>
                        </div>

                        <div class="text-center">
                            <form >
                                <button class="btn btn-success px-4 py-2 rounded-pill" type="submit">
                                    <i class="bi bi-check-circle"></i> Confirmer la commande
                                </button><br><br>

                                <a href="EShop.jsp" class="btn btn-success px-4 py-2 rounded-pill">
                                    <i ></i> Retour Catalogue
                                </a>
                            </form>
                        </div>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-warning text-center mt-5">Aucun article à valider.</div>
                </c:otherwise>
            </c:choose>
        </div>

        <footer class="mt-5">
            © 2025 CDShop - Tous droits réservés
        </footer>

        <!--  Scripts Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
