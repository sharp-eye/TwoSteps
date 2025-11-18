package com.gestionusuarios.servlet;

import com.gestionusuarios.dao.UsuarioDAO;
import com.gestionusuarios.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");

        UsuarioDAO dao = new UsuarioDAO();

        try {
            Usuario u = dao.login(usuario, contrasena);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogeado", u);
                resp.sendRedirect("perfil.jsp");
            } else {
                //Aqui podria agregar algun parametro de ?error=1 y mostrar mensaje en el JSP
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("login.jsp");
        }
    }
}
