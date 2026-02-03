<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Merci pour votre don</title>

    <style>
        body {
            background: linear-gradient(to right, #27ae60, #2ecc71);
            font-family: 'Segoe UI', sans-serif;
        }

        .box {
            max-width: 500px;
            margin: 100px auto;
            background: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0 20px 40px rgba(0,0,0,0.15);
        }

        h1 {
            color: #27ae60;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: white;
            background: #27ae60;
            padding: 12px 20px;
            border-radius: 6px;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="box">
    <h1>Merci ❤️</h1>

    <p>
        Votre don de <strong>${montant} MRU</strong>
        a bien été enregistré.
    </p>

    <a href="${pageContext.request.contextPath}/">
        Retour à l’accueil
    </a>
</div>

</body>
</html>
