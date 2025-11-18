<%@ page import="com.gestionusuarios.model.Usuario" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioLogeado");
    if (u == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de usuario</title>
</head>
<body>
<h2>Información del usuario</h2>

<p>Nombres: <%= u.getNombres() %></p>
<p>Apellidos: <%= u.getApellidos() %></p>
<p>Sexo: <%= u.getSexo() %></p>
<p>Tipo de documento: <%= u.getTipoDocumento() %></p>
<p>Número de documento: <%= u.getNumeroDocumento() %></p>
<p>Usuario: <%= u.getUsuario() %></p>
<p>Contraseña: <%= u.getContrasena() %></p>

<a href="index.jsp">Cerrar sesión</a>
</body>
</html>
