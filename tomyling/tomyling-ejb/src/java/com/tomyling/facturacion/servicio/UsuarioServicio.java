/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.UsuarioDao;
import com.tomyling.facturacion.modelo.Usuario;

/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class UsuarioServicio extends UsuarioDao {

    public Usuario existeUsuario(String nombre, String clave) {
        Usuario usuario;
        usuario = validarExitenciaUsuario(nombre, clave);
        if (usuario == null) {
            return null;
        } else {
            return usuario;
        }

    }
    
    public void crearUsuario(Usuario u){
        try {
            this.create(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
