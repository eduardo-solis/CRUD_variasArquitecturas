/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.conexion_mysql.ConexionMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author solis
 */
public class UsuarioDao {

    public boolean insert(Usuario u) {
        u.setId(0);
        String sql = "{call registrar (?,?,?,?," //Datos
                + "?)}"; //Valor de retorno

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn;
        try {
            conn = connMySQL.abrir();
            CallableStatement cstmt = conn.prepareCall(sql);

            cstmt.setString(1, u.getNombre());
            cstmt.setString(2, u.getNombreUsuario());
            cstmt.setString(3, u.getFechaNac());
            cstmt.setString(4, u.getCorreo());

            cstmt.registerOutParameter(5, Types.INTEGER);

            cstmt.execute();

            u.setId(cstmt.getInt(5));

            cstmt.close();
            connMySQL.cerrar();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (0 > u.getId());
    }

    public void update(Usuario u) {
        String sql = "{call actualizar (?,?,?,?," //Datos
                + "?)}"; //Valor de retorno

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn;
        try {
            conn = connMySQL.abrir();
            CallableStatement cstmt = conn.prepareCall(sql);

            cstmt.setString(1, u.getNombre());
            cstmt.setString(2, u.getNombreUsuario());
            cstmt.setString(3, u.getFechaNac());
            cstmt.setString(4, u.getCorreo());
            cstmt.setInt(5, u.getId());

            cstmt.execute();

            cstmt.close();
            connMySQL.cerrar();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Usuario> getAllUsers() {
        String sql = "SELECT * FROM usuario";

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn;

        List<Usuario> usuarios = new ArrayList<>();
        Usuario u = null;
        
        try {
            conn = connMySQL.abrir();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                u = fill(rs);
                usuarios.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }

    public Usuario getUser(int id) {
        String sql = "SELECT * FROM usuario WHERE id = " + id;

        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn;
        Usuario u = null;

        try {
            conn = connMySQL.abrir();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                u = fill(rs);
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;

    }

    public Usuario fill(ResultSet rs) throws Exception {
        Usuario u = new Usuario();

        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setFechaNac(rs.getString("fechaNac"));
        u.setCorreo(rs.getString("correo"));

        return u;
    }

}
