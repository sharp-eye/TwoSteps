package com.gestionusuarios.dao;

import com.gestionusuarios.config.ConexionBD;
import com.gestionusuarios.model.Usuario;
import com.gestionusuarios.util.CryptoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrar(Usuario u) throws Exception {

        String sql = "INSERT INTO usuarios " +
                "(nombres, apellidos, sexo, tipo_documento, numero_documento, " +
                " usuario_encriptado, contrasena_encriptada) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombres());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getSexo());
            ps.setString(4, u.getTipoDocumento());
            ps.setString(5, u.getNumeroDocumento());
            ps.setString(6, CryptoUtil.encrypt(u.getUsuario()));
            ps.setString(7, CryptoUtil.encrypt(u.getContrasena()));

            int filas = ps.executeUpdate();
            System.out.println("DEBUG registrar(): filas afectadas = " + filas);
            return filas == 1;

        } catch (SQLException e) {
            System.out.println("ERROR SQL en registrar(): " + e.getMessage());
            throw e;
        }
    }

    // login()
    public Usuario login(String usuario, String contrasena) throws Exception {
        String usuarioEnc = CryptoUtil.encrypt(usuario);
        String contrasenaEnc = CryptoUtil.encrypt(contrasena);

        String sql = "SELECT * FROM usuarios " +
                     "WHERE usuario_encriptado = ? AND contrasena_encriptada = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuarioEnc);
            ps.setString(2, contrasenaEnc);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombres(rs.getString("nombres"));
                    u.setApellidos(rs.getString("apellidos"));
                    u.setSexo(rs.getString("sexo"));
                    u.setTipoDocumento(rs.getString("tipo_documento"));
                    u.setNumeroDocumento(rs.getString("numero_documento"));
                    u.setUsuario(CryptoUtil.decrypt(rs.getString("usuario_encriptado")));
                    u.setContrasena(CryptoUtil.decrypt(rs.getString("contrasena_encriptada")));
                    return u;
                }
            }
        }
        return null;
    }
}
