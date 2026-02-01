<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Faire un don</title>

    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f4f6f9;
            margin: 0;
        }
        .container {
            max-width: 600px;
            margin: 60px auto;
            background: white;
            padding: 35px;
            border-radius: 14px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #27ae60;
        }
        .info {
            margin: 20px 0;
            color: #555;
        }
        label {
            display: block;
            margin-top: 20px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
        button {
            margin-top: 30px;
            width: 100%;
            padding: 14px;
            background: #27ae60;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background: #1e8449;
        }
    </style>
</head>

<body>

<div class="container">
    <h1>Faire un don ❤️</h1>

    <div class="info">
        <strong>Cas :</strong> ${cas.titre}<br>
        <strong>Localisation :</strong> ${cas.localisation}
    </div>

    <form action="${pageContext.request.contextPath}/donate/confirm" method="post">
        <input type="hidden" name="caseId" value="${cas.id}">

        <label>Montant du don (MAD)</label>
        <input type="number" name="montant" min="10" required>

        <button type="submit">
            Confirmer le don
        </button>
    </form>
</div>

</body>
</html>
