/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_model;

/**
 *
 * @author solis
 */
public class UsuarioCreateViewModel {
    
    private String nombre;
    private String nombreUsuario;
    private String fechaNac;
    private String correo;

    public UsuarioCreateViewModel(String nombre, String nombreUsuario, String fechaNac, String correo) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.fechaNac = fechaNac;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
