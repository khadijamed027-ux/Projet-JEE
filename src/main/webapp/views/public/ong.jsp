<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>ONG partenaires</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
        }

        header {
            background: linear-gradient(135deg, #27ae60, #1e8449);
            color: white;
            padding: 40px 20px;
            text-align: center;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: auto;
            padding: 40px 0;
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        .ongs {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 25px;
        }

        .ong {
            background: white;
            border-radius: 12px;
            padding: 25px;
            box-shadow: 0 6px 18px rgba(0,0,0,0.08);
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .ong:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.12);
        }

        .ong h3 {
            color: #27ae60;
            margin-bottom: 10px;
        }

        .badge {
            display: inline-block;
            padding: 5px 12px;
            background: #ecf0f1;
            border-radius: 20px;
            font-size: 12px;
            margin-bottom: 15px;
            color: #555;
        }

        .ong p {
            color: #555;
            line-height: 1.5;
        }

        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 16px;
            background: #27ae60;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
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
    </style>
</head>
<body>

<header>
    <h1>Plateforme de Solidarité</h1>
    <p>Découvrez les ONG partenaires et soutenez leurs actions</p>
</header>

<div class="container">
    <h2>ONG partenaires</h2>

    <c:if test="${empty ongs}">
        <div class="empty">
            Aucune ONG disponible pour le moment.
        </div>
    </c:if>

    <div class="ongs">
        <c:forEach var="o" items="${ongs}">
            <div class="ong">
                <h3>${o.nom}</h3>
                <div class="badge">${o.domaine}</div>
                <p>${o.description}</p>

                <a class="btn"
                   href="${pageContext.request.contextPath}/ong/public?id=${o.id}">
                    Voir les cas →
                </a>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
