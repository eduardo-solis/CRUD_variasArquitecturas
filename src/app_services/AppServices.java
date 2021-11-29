/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_services;

import command_handler.UsuarioCommandHandler;
import commands.UsuarioActualizacionCommand;
import commands.UsuarioRegistroCommand;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view_model.UsuarioCreateViewModel;
import view_model.UsuarioPublicViewModel;
import view_model.UsuarioUpdateViewModel;
import view_model.UsuarioViewModel;

/**
 *
 * @author solis
 */
public class AppServices {
    
    public String save(UsuarioCreateViewModel modelo)
    {
        UsuarioRegistroCommand command = new UsuarioRegistroCommand
        (
                modelo.getNombre(), modelo.getNombreUsuario(), modelo.getFechaNac(), modelo.getCorreo()
        );
        
        UsuarioCommandHandler chandler = new UsuarioCommandHandler();
        
        return chandler.save(command);
    }
    
    public String update(UsuarioUpdateViewModel modelo)
    {
        UsuarioActualizacionCommand command = new UsuarioActualizacionCommand
        (
                modelo.getId(), modelo.getNombreUsuario(), modelo.getCorreo()
        );
        
        UsuarioCommandHandler chandler = new UsuarioCommandHandler();
        return chandler.update(command);
    }
    
    public ObservableList getUsers()
    {
        UsuarioCommandHandler chandler = new UsuarioCommandHandler();
        List<UsuarioViewModel> usuarios = new ArrayList<>();
        ObservableList<UsuarioViewModel> listaInterna = FXCollections.observableArrayList();
        
        usuarios = chandler.getUsers();
        
        for(UsuarioViewModel modelo : usuarios)
        {
            UsuarioViewModel u = new UsuarioViewModel(modelo.getId(),modelo.getNombre(), modelo.getNombreUsuario(), modelo.getFechaNac(), modelo.getCorreo());
            listaInterna.add(u);
        }
        
        return listaInterna;
    }
    
    public ObservableList getUsersPublic()
    {
        UsuarioCommandHandler chandler = new UsuarioCommandHandler();
        List<UsuarioPublicViewModel> usuarios = new ArrayList<>();
        ObservableList<UsuarioPublicViewModel> listaPublica = FXCollections.observableArrayList();
        
        usuarios = chandler.getUsersPublic();
        
        for(UsuarioPublicViewModel modelo : usuarios)
        {
            UsuarioPublicViewModel u = new UsuarioPublicViewModel(modelo.getId(), modelo.getNombreUsuario(), modelo.getCorreo());
            listaPublica.add(u);
        }
        
        
        return listaPublica;
    }
    
    public UsuarioViewModel getUser(int id)
    {
        UsuarioCommandHandler chandler = new UsuarioCommandHandler();
        UsuarioViewModel usuario = new UsuarioViewModel();
        
        usuario = chandler.getUser(id);
        return usuario;
    }
    
    public UsuarioPublicViewModel getUserPublic(int id)
    {
        UsuarioCommandHandler chandler = new UsuarioCommandHandler();
        UsuarioPublicViewModel usuario = new UsuarioPublicViewModel();
        
        usuario = chandler.getUserPublic(id);
        return usuario;
    }
}
