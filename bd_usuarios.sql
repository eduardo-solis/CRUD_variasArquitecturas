drop database if exists usuarios;
create database usuarios;
use usuarios;

create table usuario(
	id int not null auto_increment primary key,
	nombre varchar(50) not null,
    nombreUsuario varchar(25) not null,
    fechaNac varchar(10) not null,
    correo varchar(30) not null
);

# STORED PROCEDURE DE USUARIO

Delimiter $$
create procedure registrar
	(
		IN var_nombre varchar(50),
        IN var_nombreUsuario varchar(25),
        IN var_fechaNac varchar(10),
        IN var_correo varchar(30),
        
        OUT var_id int
    )
Begin
	insert into usuario (nombre, nombreUsuario, fechaNac, correo)
		values (var_nombre, var_nombreUsuario, var_fechaNac, var_correo);
	
    Set @var_id = last_insert_id();
    
End
$$
Delimiter ;

Delimiter $$
create procedure actualizar
	(
		IN var_nombre varchar(50),
        IN var_nombreUsuario varchar(25),
        IN var_fechaNac varchar(10),
        IN var_correo varchar(30),
        
        IN var_id int
    )
Begin
	
    update usuario 
    
		set nombre = var_nombre,
			nombreUsuario = var_nombreUsuario,
            fechaNac = var_fechaNac,
            correo = var_correo
            
		where id = var_id;
        
End
$$
Delimiter ;
