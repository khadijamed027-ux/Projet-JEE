<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ongconnect.model.CaseReport" %>
<%@ page import="java.util.List" %>
<%
    List<CaseReport> cases = (List<CaseReport>) request.getAttribute("cases");
    if(cases == null) cases = new java.util.ArrayList<>();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard ONG</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Dashboard ONG - Liste des cas</h2>
    <table class="table table-bordered table-striped mt-3">
        <thead>
            <tr>
                <th>Titre</th>
                <th>Description</th>
                <th>Localisation</th>
                <th>Statut</th>
            </tr>
        </thead>
        <tbody>
        <% for(CaseReport c : cases) { %>
            <tr>
                <td><%= c.getTitre() %></td>
                <td><%= c.getDescription() %></td>
                <td><%= c.getLocalisation() %></td>
                <td><%= c.getStatut() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <a href="<%= request.getContextPath() %>/jsp/ong/ong-dashboard.jsp" class="btn btn-secondary mt-3">Retour au dashboard</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>



</body>
</html>