<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>${cd != null ? 'Modifier' : 'Ajouter'} un CD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">
                            <i class="bi bi-disc"></i> 
                            ${cd != null ? 'Modifier un CD' : 'Ajouter un CD'}
                        </h3>
                    </div>
                    <div class="card-body">
                        <form action="CDAdminServlet" method="post">
                            
                            <c:if test="${cd != null}">
                                <input type="hidden" name="action" value="update" />
                                <input type="hidden" name="id" value="${cd.id}" />
                            </c:if>
                            <c:if test="${cd == null}">
                                <input type="hidden" name="action" value="insert" />
                            </c:if>

                            <div class="mb-3">
                                <label for="album" class="form-label">
                                    <i class="bi bi-music-note-beamed"></i> Album *
                                </label>
                                <input type="text" 
                                       class="form-control" 
                                       id="album" 
                                       name="album" 
                                       value="${cd != null ? cd.album : ''}" 
                                       required>
                            </div>

                            <div class="mb-3">
                                <label for="artist" class="form-label">
                                    <i class="bi bi-person"></i> Artiste *
                                </label>
                                <input type="text" 
                                       class="form-control" 
                                       id="artist" 
                                       name="artist" 
                                       value="${cd != null ? cd.artist : ''}" 
                                       required>
                            </div>

                            <div class="mb-3">
                                <label for="country" class="form-label">
                                    <i class="bi bi-globe"></i> Pays *
                                </label>
                                <input type="text" 
                                       class="form-control" 
                                       id="country" 
                                       name="country" 
                                       value="${cd != null ? cd.country : ''}" 
                                       required>
                            </div>

                            <div class="mb-3">
                                <label for="price" class="form-label">
                                    <i class="bi bi-currency-dollar"></i> Prix *
                                </label>
                                <input type="number" 
                                       step="0.01" 
                                       class="form-control" 
                                       id="price" 
                                       name="price" 
                                       value="${cd != null ? cd.price : ''}" 
                                       required>
                            </div>

                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-check-circle"></i> 
                                    ${cd != null ? 'Mettre à jour' : 'Ajouter'}
                                </button>
                                <a href="CDAdminServlet?action=list" class="btn btn-secondary">
                                    <i class="bi bi-x-circle"></i> Annuler
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    
</body>
</html>