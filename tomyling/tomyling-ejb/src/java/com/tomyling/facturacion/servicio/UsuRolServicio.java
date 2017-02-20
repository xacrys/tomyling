/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.UsuRolDao;
import com.tomyling.facturacion.modelo.UsuRol;

/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class UsuRolServicio extends UsuRolDao {

    public UsuRol obtenerUsuRol(Integer idUsuario) {
        try {
            UsuRol ur = new UsuRol();
            ur = this.obtenerUsuPorRol(idUsuario);
            if (ur == null) {
              return null;
            }
            else{
                return ur;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problema al obtener usuario por rol en UsuRolServicio");
            return null;
        }
    }

}
