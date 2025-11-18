<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de usuario</title>
</head>
<body>
<h2>Registrarse</h2>

<form action="registrar" method="post">
    Nombres: <input type="text" name="nombres" required><br>
    Apellidos: <input type="text" name="apellidos" required><br>

    Sexo:
    <select name="sexo">
        <option value="Femenino">Femenino</option>
        <option value="Masculino">Masculino</option>
    </select><br>

    Tipo de documento:
    <select name="tipoDocumento">
        <option value="Cédula de Ciudadanía">Cédula de Ciudadanía</option>
        <option value="Tarjeta de Identidad">Tarjeta de Identidad</option>
    </select><br>

    Número de documento:
    <input type="text" name="numeroDocumento" required><br>

    Usuario: <input type="text" name="usuario" required><br>
    Contraseña: <input type="password" name="contrasena" required><br><br>

    <button type="submit">Registrar</button>
</form>

<a href="index.jsp">Volver al inicio</a>
</body>
</html>
