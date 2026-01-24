<%@ page import="java.util.*, com.ongconnect.model.*" %>

<h2>Cas signalés à assigner</h2>

<% List<CaseReport> cases = (List<CaseReport>) request.getAttribute("cases");
   List<ONG> ongs = (List<ONG>) request.getAttribute("ongs");
%>

<% for(CaseReport c : cases) { %>
    <div class="card mb-3">
        <div class="card-body">
            <h5><%= c.getTitre() %></h5>
            <p><%= c.getDescription() %></p>

            <form method="post" action="<%= request.getContextPath() %>/admin/assign">
                <input type="hidden" name="caseId" value="<%= c.getId() %>"/>

                <select name="ongId" required>
                    <option value="">-- Choisir ONG --</option>
                    <% for(ONG ong : ongs) { %>
                        <option value="<%= ong.getId() %>">
                            <%= ong.getNom() %>
                        </option>
                    <% } %>
                </select>

                <button type="submit" class="btn btn-primary btn-sm">Assigner</button>
            </form>
        </div>
    </div>
<% } %>