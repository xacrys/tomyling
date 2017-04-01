/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.RolMenuDao;
import com.tomyling.facturacion.modelo.RolMenu;
import java.util.List;

/**
 *
 * @author new user
 */

@LocalBean
@Stateless
public class RolMenuServicio extends RolMenuDao 
{

    public List<RolMenu> obtenerRolMenu(Integer idRol) {
        List<RolMenu> rm = this.obtenerRolPorMenu(idRol);
        if (rm == null) {
            return null;
        } else {
        return rm;
        }

    }
  
    public List<RolMenu> consultarRolesPorUsuario(Integer idUsuario)
    {
        List<RolMenu> lstRolMenu;
        lstRolMenu=this.listaRolMenu(idUsuario); 
        if(lstRolMenu==null || lstRolMenu.isEmpty())
        {
            return null;
        } 
        else
        {
            
        }   return lstRolMenu;     
    }        
    
   public void guardarRolMenu(RolMenu rm)
   {
       this.create(rm);
   }        
          
}
