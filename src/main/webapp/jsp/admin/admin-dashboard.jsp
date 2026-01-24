<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.ongconnect.model.*" %>
<%
    User admin = (User) session.getAttribute("user");
    if(admin == null || admin.getRole() != Role.ADMIN){
        response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
        return;
    }

    List<ONG> ongs = (List<ONG>) request.getAttribute("ongs");
    ongs = new ArrayList<>();  // ⚡ Initialiser pour éviter NullPointerException
    List<CaseReport> cases = (List<CaseReport>) request.getAttribute("cases");
    if(cases == null) {
        cases = new ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">

    <h2>Validation des ONG & Assignation des cas</h2>

    <!-- Cas signalés à assigner -->
    <h4 class="mt-4">Cas signalés</h4>
    <% if(cases == null || cases.isEmpty()) { %>
        <p>Aucun cas signalé pour le moment.</p>
    <% } else { %>
        <% for(CaseReport c : cases) { %>
            <div class="card mb-3">
                <div class="card-body">
                    <h5><%= c.getTitre() %></h5>
                    <p><%= c.getDescription() %></p>
                    <p><b>Localisation:</b> <%= c.getLocalisation() %></p>

                    <form method="post" action="<%= request.getContextPath() %>/admin/assign">
                        <input type="hidden" name="caseId" value="<%= c.getId() %>"/>
                        <select name="ongId" required>
                            <option value="">-- Choisir ONG --</option>
                            <% for(ONG ong : ongs) { %>
                                <option value="<%= ong.getId() %>"><%= ong.getNom() %></option>
                            <% } %>
                        </select>
                        <button type="submit" class="btn btn-primary btn-sm">Assigner</button>
                    </form>
                </div>
            </div>
        <% } %>
    <% } %>

    <!-- Ong validées -->
    <h4 class="mt-5">ONG enregistrées</h4>
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Statut</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% for(ONG o : ongs) { %>
                <tr>
                    <td><%= o.getNom() %></td>
                    <td><%= o.getStatutValidation() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/admin/validate?id=<%= o.getId() %>" class="btn btn-sm btn-success">Valider</a>
                        <a href="<%= request.getContextPath() %>/admin/refuse?id=<%= o.getId() %>" class="btn btn-sm btn-danger">Refuser</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>