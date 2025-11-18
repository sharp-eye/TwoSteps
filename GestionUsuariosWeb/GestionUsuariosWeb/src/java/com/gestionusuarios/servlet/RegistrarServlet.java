package com.gestionusuarios.servlet;

import com.gestionusuarios.dao.UsuarioDAO;
import com.gestionusuarios.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegistrarServlet", urlPatterns = {"/registrar"})
public class RegistrarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Usuario u = new Usuario();
        u.setNombres(req.getParameter("nombres"));
        u.setApellidos(req.getParameter("apellidos"));
        u.setSexo(req.getParameter("sexo"));
        u.setTipoDocumento(req.getParameter("tipoDocumento"));
        u.setNumeroDocumento(req.getParameter("numeroDocumento"));
        u.setUsuario(req.getParameter("usuario"));
        u.setContrasena(req.getParameter("contrasena"));

        UsuarioDAO dao = new UsuarioDAO();
        try {
            boolean ok = dao.registrar(u);
            if (ok) {
                resp.sendRedirect("login.jsp?registroOk=1");
            } else {
                //No lanzo la excepción, pero tampoco inserto filas
                resp.sendRedirect("registro.jsp?error=sinFilas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("registro.jsp?error=1");
        }
    }

}
