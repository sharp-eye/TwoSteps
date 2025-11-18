<%@ page import="com.gestionusuarios.config.ConexionBD" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de usuarios</title>
</head>
<body>
<h2>Usuarios registrados (según la aplicación)</h2>

<%
    try (Connection con = ConexionBD.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT * FROM usuarios")) {
%>
        <table border="1" cellpadding="4">
            <tr>
                <th>ID</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Sexo</th>
                <th>Tipo Doc</th>
                <th>Nro Doc</th>
                <th>Usuario (encriptado)</th>
                <th>Contraseña (encriptada)</th>
            </tr>
        <%
            boolean hayFilas = false;
            while (rs.next()) {
                hayFilas = true;
        %>
                <tr>
                    <td><%= rs.getInt("id") %></td>
                    <td><%= rs.getString("nombres") %></td>
                    <td><%= rs.getString("apellidos") %></td>
                    <td><%= rs.getString("sexo") %></td>
                    <td><%= rs.getString("tipo_documento") %></td>
                    <td><%= rs.getString("numero_documento") %></td>
                    <td><%= rs.getString("usuario_encriptado") %></td>
                    <td><%= rs.getString("contrasena_encriptada") %></td>
                </tr>
        <%
            }
            if (!hayFilas) {
        %>
                <tr><td colspan="8">No hay usuarios registrados.</td></tr>
        <%
            }
        %>
        </table>
<%
    } catch (Exception e) {
        out.println("<p>Error consultando usuarios: " + e.getMessage() + "</p>");
        e.printStackTrace();
    }
%>

</body>
</html>
