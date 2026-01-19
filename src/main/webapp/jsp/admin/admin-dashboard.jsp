<%@ page contentType="text/html;charset=UTF-8" %>
<%
    com.ongconnect.model.User user = (com.ongconnect.model.User) session.getAttribute("user");
    if(user == null || !user.getRole().name().equals("ADMIN")) {
        response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administrateur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="card-title mb-4">Dashboard Administrateur</h2>
            <p>Bienvenue : <b><%= user.getNom() %></b></p>

            <ul class="list-group mb-3">
                <li class="list-group-item">
                    <a href="<%= request.getContextPath() %>/jsp/admin/ong-validation.jsp">Validation des ONG</a>
                </li>
            </ul>

            <a href="<%= request.getContextPath() %>/logout" class="btn btn-danger">Déconnexion</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>