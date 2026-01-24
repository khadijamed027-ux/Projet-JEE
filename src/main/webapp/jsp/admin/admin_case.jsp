<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ongconnect.model.*" %>

<html>
<head>
    <title>Validation des cas</title>
</head>
<body>

<h2>Cas signalés</h2>

<table border="1">
<tr>
    <th>Titre</th>
    <th>Localisation</th>
    <th>Assigner à une ONG</th>
</tr>

<%
    List<CaseReport> cases = (List<CaseReport>) request.getAttribute("cases");
    List<ONG> ongs = (List<ONG>) request.getAttribute("ongs");

    for (CaseReport c : cases) {
%>
<tr>
    <td><%= c.getTitre() %></td>
    <td><%= c.getLocalisation() %></td>
    <td>
        <form action="<%=request.getContextPath()%>/admin/assign" method="post">
            <input type="hidden" name="caseId" value="<%= c.getId() %>"/>

            <select name="ongId" required>
                <% for (ONG o : ongs) { %>
                    <option value="<%= o.getId() %>"><%= o.getNom() %></option>
                <% } %>
            </select>

            <button type="submit">Assigner</button>
        </form>
    </td>
</tr>
<% } %>

</table>

</body>
</html>