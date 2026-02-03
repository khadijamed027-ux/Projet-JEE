<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notifications</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background: #f4f6f9;
}

.header {
    background: linear-gradient(135deg,#6a11cb,#2575fc);
    color:white;
    padding:30px;
    text-align:center;
}

.card-notif {
    border-left:5px solid #2575fc;
    margin-bottom:10px;
}

.empty {
    padding:40px;
    text-align:center;
    color:gray;
}
</style>

</head>

<body>

<div class="header">
    <h2>📩 Notifications</h2>
</div>

<div class="container mt-4">

<c:if test="${empty notifications}">
<div class="empty">
    <h4>Aucune notification</h4>
</div>
</c:if>

<c:forEach var="n" items="${notifications}">

<div class="card card-notif p-3">

    <div class="d-flex justify-content-between">

        <div>
            <strong>${n.message}</strong>
        </div>

        <c:if test="${!n.lu}">
            <span class="badge bg-danger">Nouveau</span>
        </c:if>

        <c:if test="${n.lu}">
            <span class="badge bg-secondary">Lu</span>
        </c:if>

    </div>

</div>

</c:forEach>

<a class="btn btn-primary mt-3"
   href="${pageContext.request.contextPath}/">
   ← Retour
</a>

</div>

</body>
</html>
