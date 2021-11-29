/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command_handler;

import commands.UsuarioActualizacionCommand;
import commands.UsuarioRegistroCommand;
import dao.UsuarioDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import view_model.UsuarioPublicViewModel;
import view_model.UsuarioViewModel;

/**
 *
 * @author solis
 */
public class UsuarioCommandHandler {
    
    public String save(UsuarioRegistroCommand command)
    {
        LocalDate localDate = LocalDate.now();
        
        int tamanioNombre = command.getNombre().length();
        int tamanioCorreo = command.getCorreo().length();
        int edad = localDate.getYear() - Integer.parseInt(command.getFechaNac().substring(6));
        
        if(tamanioNombre > 100)
        {
            return "El nombre solo puede tener un maximo de 100 caracteres";
        }
        if(tamanioNombre == 0)
        {
            return "Es necesario ingresar un nombre";
        }
        
        if(tamanioCorreo > 100)
        {
            return "El correo solo puede tener un maximo de 100 caracteres";
        }
        if(tamanioCorreo == 0)
        {
            return "Es necesario ingresar un correo";
        }
        
        if(edad < 18)
        {
            return "El usuario debe ser mayor de edad";
        }
        
        Usuario u = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        
        u.setNombre(command.getNombre());
        u.setNombreUsuario(command.getNombreUsuario());
        u.setFechaNac(command.getFechaNac());
        u.setCorreo(command.getCorreo());
        
        if(dao.insert(u) == false)
        {
            return "OK";
        }
        else
        {
            return "No se pudo registrar al usuario";
        }
        
    }
    
    public String update (UsuarioActualizacionCommand command)
    {
        Usuario u = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        
        u = dao.getUser(command.getId());
        
        if(u.getNombre().isEmpty())
        {
            return "No existe este usuario";
        }
        
        u.setId(command.getId());
        u.setNombreUsuario(command.getNombreUsuario());
        u.setCorreo(command.getCorreo());
        
        dao.update(u);
        
        return "OK";
    }
    
    public List getUsers()
    {
        UsuarioDao dao = new UsuarioDao();
        
        List<UsuarioViewModel> usuariosModelo = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        
        usuarios = dao.getAllUsers();
        
        for(Usuario u : usuarios)
        {
            UsuarioViewModel modelo = new UsuarioViewModel();
            modelo.setId(u.getId());
            modelo.setNombre(u.getNombre());
            modelo.setNombreUsuario(u.getNombreUsuario());
            modelo.setFechaNac(u.getFechaNac());
            modelo.setCorreo(u.getCorreo());
            
            usuariosModelo.add(modelo);
        }
        
        return usuariosModelo;
    }
    
    public List getUsersPublic()
    {
        UsuarioDao dao = new UsuarioDao();
        
        List<UsuarioPublicViewModel> usuariosModelo = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        
        usuarios = dao.getAllUsers();
        
        for(Usuario u : usuarios)
        {
            UsuarioPublicViewModel modelo = new UsuarioPublicViewModel();
            modelo.setId(u.getId());
            modelo.setNombreUsuario(u.getNombreUsuario());
            modelo.setCorreo(u.getCorreo());
            
            usuariosModelo.add(modelo);
        }
        
        return usuariosModelo;
    }
    
    public UsuarioViewModel getUser(int id)
    {
        UsuarioDao dao = new UsuarioDao();
        UsuarioViewModel modelo = new UsuarioViewModel();
        
        Usuario u = dao.getUser(id);
        
        modelo.setId(u.getId());
        modelo.setNombre(u.getNombre());
        modelo.setNombreUsuario(u.getNombreUsuario());
        modelo.setFechaNac(u.getFechaNac());
        modelo.setCorreo(u.getCorreo());
        
        return modelo;
    }
    
    public UsuarioPublicViewModel getUserPublic(int id)
    {
        UsuarioDao dao = new UsuarioDao();
        UsuarioPublicViewModel modelo = new UsuarioPublicViewModel();
        
        Usuario u = dao.getUser(id);
        
        modelo.setId(u.getId());
        modelo.setNombreUsuario(u.getNombreUsuario());
        
        return modelo;
    }
    
}
