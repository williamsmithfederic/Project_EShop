<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>E-Shop - Connexion</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

        <style>
            body {
               
                display: flex;
                align-items: center;
                justify-content: center;

              
            }

            .login-card {
                background: white;
                border-radius: 15px;
                box-shadow: 0 10px 25px rgba(0,0,0,0.2);
                padding: 40px;
                max-width: 400px;
                width: 100%;
            }

            .login-header {
                text-align: center;
                margin-bottom: 30px;
            }

            .login-header i {
                font-size: 4rem;
                color: #667eea;
            }

            .login-header h2 {
                color: #333;
                margin-top: 15px;
            }

            .btn-login {
                background: #ff8a3d;
                border: none;
                padding: 12px;
                font-size: 1.1rem;
            }

            .btn-login:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            }
        </style>
    </head>
    <body>
        <div class="login-card">
            <div class="login-header">
                <i class="bi bi-disc-fill"></i>
                <h2>E-Shop</h2>
                <p class="text-muted">Connectez-vous pour continuer</p>
            </div>

            <!-- Message d'erreur -->
            <c:if test="${param.error != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-triangle"></i>
                    <strong>Erreur !</strong> Nom d'utilisateur ou mot de passe incorrect.
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Message de déconnexion -->
            <c:if test="${param.logout != null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle"></i>
                    Vous avez été déconnecté avec succès.
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <!-- Formulaire de connexion -->
            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">
                        <i class="bi bi-person"></i> Nom d'utilisateur
                    </label>
                    <input type="text" 
                           class="form-control form-control-lg" 
                           id="username" 
                           name="username" 
                           placeholder="Entrez votre nom d'utilisateur"
                           required 
                           autofocus>
                </div>

                <div class="mb-4">
                    <label for="password" class="form-label">
                        <i class="bi bi-lock"></i> Mot de passe
                    </label>
                    <input type="password" 
                           class="form-control form-control-lg" 
                           id="password" 
                           name="password" 
                           placeholder="Entrez votre mot de passe"
                           required>
                </div>

                <button type="submit" class="btn btn-primary btn-login w-100">
                    <i class="bi bi-box-arrow-in-right"></i> Se connecter
                </button>
            </form>

            <hr class="my-4">

            <div class="text-center text-muted">
                <small>
                    <i class="bi bi-info-circle"></i> 
                    Identifiants par défaut :<br>
                    <strong>admin</strong> / <strong>123</strong></br>
                    <strong>user</strong> / <strong>123</strong>
                </small>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>