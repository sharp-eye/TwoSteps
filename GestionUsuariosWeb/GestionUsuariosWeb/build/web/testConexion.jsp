<%@ page import="com.gestionusuarios.config.ConexionBD" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prueba de conexión</title>
</head>
<body>
<h2>Probando conexión a la base de datos</h2>

<%
    Connection con = null;
    try {
        con = ConexionBD.getConnection();
        out.println("<p>✅ Conexión exitosa a la base de datos gestion_usuarios.</p>");
    } catch (Exception e) {
        out.println("<p>❌ Error de conexión: " + e.getMessage() + "</p>");
        //ver el detalle en el log del servidor:
        e.printStackTrace();
    } finally {
        if (con != null) {
            try { con.close(); } catch (Exception ex) {}
        }
    }
%>

</body>
</html>
