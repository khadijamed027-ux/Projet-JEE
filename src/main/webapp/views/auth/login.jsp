<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion - ONG Connect</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #27ae60, #2ecc71);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-box {
            background: white;
            padding: 35px;
            width: 380px;
            border-radius: 15px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            color: #27ae60;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 12px;
            border-radius: 8px;
            border: 1px solid #ddd;
        }

        button {
            width: 100%;
            margin-top: 20px;
            padding: 12px;
            background: #27ae60;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }

        .error {
            color: red;
            margin-top: 10px;
        }

        .link {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>

<body>

<div class="login-box">

    <h2>Connexion</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <input type="email" name="email" placeholder="Email" required>

        <input type="password" name="password" placeholder="Mot de passe" required>

        <button type="submit">Se connecter</button>

    </form>

    <div class="link">
        Pas de compte ?
        <a href="${pageContext.request.contextPath}/views/auth/register.jsp">
            S’inscrire
        </a>
    </div>

</div>

</body>
</html>
