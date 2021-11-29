/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.conexion_mysql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author solis
 */
public class ConexionMySQL {
    private Connection conn;
    static final String USUARIO = "root";
    static final String CONTRASENIA = "root";
    static final String URL = "jdbc:mysql://localhost:3306/usuarios";
    
    public Connection abrir() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
        System.out.println("Conexión con la base de datos abierta");
        return this.conn;
    }
    
    public void cerrar()
    {
        try 
        {
            this.conn.close();
            System.out.println("Conexión con la base de datos cerrada");
        } 
        catch (Exception e) 
        {
            System.out.println("Ah ocurrido un error al cerrar la conexion con la base de datos");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) 
    {
        try 
        {
            ConexionMySQL connMySQL = new ConexionMySQL();
            
            connMySQL.abrir();
            connMySQL.cerrar();
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
