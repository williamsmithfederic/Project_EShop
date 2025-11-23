<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header_user.jsp" %>

<div class="container my-5">
    <h3 class="text-center text-primary mb-4"><i class="bi bi-cart"></i> Mon Panier</h3>
    <p>Session ID : <%= session.getId() %></p>

    <c:if test="${empty sessionScope.panier.items}">
        <div class="alert alert-info text-center">Votre panier est vide.</div>
    </c:if>

    <c:if test="${not empty sessionScope.panier.items}">
        <table class="table table-hover shadow-sm bg-white rounded">
            <thead class="table-primary">
                <tr>
                    <th>Album</th>
                    <th>Artiste</th>
                    <th>Prix ($)</th>
                    <th>Quantité</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cd" items="${sessionScope.panier.items}">
                    <tr>
                        <td>${cd.album}</td>
                        <td>${cd.artist}</td>
                        <td>${cd.price}</td>
                        <td>${cd.quantity}</td>
                        <td>
                            <a href="PanierServlet?action=plus&album=${cd.album}" class="btn btn-sm btn-outline-success"><i class="bi bi-plus-circle"></i></a>
                            <a href="PanierServlet?action=moins&album=${cd.album}" class="btn btn-sm btn-outline-warning"><i class="bi bi-dash-circle"></i></a>
                            <a href="PanierServlet?action=supprimer&album=${cd.album}" class="btn btn-sm btn-outline-danger"><i class="bi bi-trash"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="d-flex justify-content-between align-items-center mt-4">
            <h5 class="text-primary">💰 Total : <strong>${sessionScope.panier.total}</strong> $</h5>

            <div>
                <a href="DetailPanierPdf" class="btn btn-success me-2"><i class="bi bi-file-earmark-pdf"></i> Générer la facture PDF</a>
                <a href="Checkout.jsp" class="btn btn-primary"><i class="bi bi-box-arrow-right"></i> Passer à la caisse</a>
            </div>
        </div>
    </c:if>
</div>


