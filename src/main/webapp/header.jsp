<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CD E-Shop</title>

        <!-- Bootstrap et icônes -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

        <style>
            body {
                background-color: #f8f9fa;
            }
            .navbar {
                background-color: #ff8a3d !important;
            }
            .navbar-brand, .nav-link, .nav-link i {
                color: white !important;
            }
            .nav-link:hover {
                text-decoration: underline;
            }
            .cart-count {
                background: #ffc107;
                color: black;
                font-size: 0.8rem;
                padding: 2px 6px;
                border-radius: 50%;
                position: relative;
                top: -10px;
                left: -8px;
            }
            /* Style pour le lien Admin */
            .admin-link {
                background-color: #dc3545 !important;
                border-radius: 5px;
                padding: 5px 15px !important;
                margin-left: 10px;
            }
            .admin-link:hover {
                background-color: #bb2d3b !important;
            }
        </style>
    </head>
    <body>

        <!-- BARRE DE NAVIGATION COMMUNE -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" >
                    CD & DVD    E-Shop
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">

                        

                        <!--  LIEN ADMINISTRATION -->
                        <li class="nav-item">
                            <a class="nav-link admin-link" href="CDAdminServlet?action=list">
                                <i class="bi bi-tools"></i> Admin
                            </a>
                        </li>

                        <!--  BOUTON DÉCONNEXION -->
                        <c:if test="${sessionScope.isLoggedIn == true}">
                            <li class="nav-item">
                                <a class="nav-link " href="LogoutServlet">
                                    <i class="bi bi-box-arrow-right"></i> Déconnexion
                                </a>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>

