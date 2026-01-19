<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ONG Connect</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .welcome-card {
            max-width: 500px;
            margin: 100px auto;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="card shadow-sm welcome-card text-center">
        <div class="card-body">
            <h1 class="card-title mb-4">Bienvenue sur ONG Connect</h1>
            <p class="mb-4">Votre plateforme de gestion des ONG et des cas signalés.</p>

            <div class="d-grid gap-2">
                <a href="${pageContext.request.contextPath}/jsp/auth/login.jsp" class="btn btn-primary btn-lg">Connexion</a>
                <a href="${pageContext.request.contextPath}/jsp/auth/register.jsp" class="btn btn-success btn-lg">Inscription</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>