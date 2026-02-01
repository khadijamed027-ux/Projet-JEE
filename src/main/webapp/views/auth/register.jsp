<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Inscription - ONG Connect</title>

<style>
    body {
        margin: 0;
        font-family: 'Segoe UI', sans-serif;
        background: linear-gradient(135deg, #2ecc71, #27ae60);
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }

    .box {
        background: white;
        padding: 30px;
        width: 420px;
        border-radius: 14px;
        box-shadow: 0 15px 35px rgba(0,0,0,0.2);
    }

    h2 {
        text-align: center;
        color: #27ae60;
    }

    input, select {
        width: 100%;
        padding: 12px;
        margin-top: 12px;
        border-radius: 8px;
        border: 1px solid #ccc;
    }

    button {
        width: 100%;
        margin-top: 18px;
        padding: 12px;
        background: #27ae60;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
    }

    .link {
        margin-top: 10px;
        text-align: center;
    }
</style>
</head>

<body>

<div class="box">

<h2>Créer un compte</h2>
<c:if test="${not empty error}">
    <div style="color:red; text-align:center;">
        ${error}
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/register" method="post">

    <input type="text" name="nom" placeholder="Nom complet" required>

    <input type="email" name="email" placeholder="Email" required>

    <input type="password" name="password" placeholder="Mot de passe" required>

    <input type="text" name="telephone"
       pattern="[234][0-9]{7}"
       title="Le numéro doit contenir 8 chiffres et commencer par 2, 3 ou 4"
       required />


    <select name="role" required>
        <option value="">-- Type de compte --</option>
        <option value="ONG">ONG</option>
    </select>

    <button type="submit">S’inscrire</button>

</form>

<div class="link">
    Déjà inscrit ?
    <a href="${pageContext.request.contextPath}/views/auth/login.jsp">
        Se connecter
    </a>
</div>

</div>

</body>
</html>
