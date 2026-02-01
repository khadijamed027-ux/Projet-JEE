<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cas de l’ONG</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: #f4f6f9;
        }

        header {
            background: #27ae60;
            padding: 20px;
            color: white;
            text-align: center;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 40px auto;
        }

        .cases {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 25px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.08);
            transition: transform 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card h3 {
            color: #2c3e50;
            margin-bottom: 10px;
        }

        .badge {
            display: inline-block;
            padding: 5px 12px;
            background: #eafaf1;
            color: #27ae60;
            border-radius: 20px;
            font-size: 12px;
            margin-bottom: 10px;
        }

        .btn {
            display: inline-block;
            margin-top: 15px;
            padding: 12px 18px;
            background: #27ae60;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }

        .btn:hover {
            background: #1e8449;
        }

        .empty {
            text-align: center;
            color: #777;
            font-size: 18px;
            margin-top: 50px;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #999;
        }
    </style>
</head>

<body>

<header>
    <h1>Cas humanitaires disponibles</h1>
    <p>Votre don peut sauver des vies ❤️</p>
</header>

<div class="container">

    <c:if test="${empty cases}">
        <div class="empty">
            Aucun cas disponible pour cette ONG pour le moment.
        </div>
    </c:if>

    <div class="cases">
        <c:forEach var="c" items="${cases}">
            <div class="card">
                <span class="badge">${c.typeCase}</span>
                <h3>${c.titre}</h3>
                <p><strong>Localisation :</strong> ${c.localisation}</p>
                <p>${c.description}</p>

                <a class="btn"
                   href="${pageContext.request.contextPath}/donate?caseId=${c.id}">
                    Faire un don ❤️
                </a>
            </div>
        </c:forEach>
    </div>

</div>

<footer>
    © 2026 ONG Connect — Plateforme solidaire
</footer>

</body>
</html>
