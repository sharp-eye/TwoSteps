<%@ page import="com.gestionusuarios.config.ConexionBD" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert directo</title>
</head>
<body>
<h2>Insertando usuario de prueba</h2>

<%
    Connection con = null;
    PreparedStatement ps = null;
    try {
        con = ConexionBD.getConnection();

        String sql = "INSERT INTO usuarios " +
                     "(nombres, apellidos, sexo, tipo_documento, numero_documento, " +
                     " usuario_encriptado, contrasena_encriptada) " +
                     "VALUES (?,?,?,?,?,?,?)";

        ps = con.prepareStatement(sql);
        ps.setString(1, "NombrePrueba");
        ps.setString(2, "ApellidoPrueba");
        ps.setString(3, "Masculino");
        ps.setString(4, "Cédula de Ciudadanía");
        ps.setString(5, "1234567890");
        ps.setString(6, "usuarioEncriptadoPrueba");
        ps.setString(7, "contrasenaEncriptadaPrueba");

        int filas = ps.executeUpdate();
        out.println("<p>Filas insertadas: " + filas + "</p>");

    } catch (Exception e) {
        out.println("<p>Error al insertar: " + e.getMessage() + "</p>");
        e.printStackTrace();
    } finally {
        if (ps != null) try { ps.close(); } catch (Exception ex) {}
        if (con != null) try { con.close(); } catch (Exception ex) {}
    }
%>

</body>
</html>
