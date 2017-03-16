/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.RolDao;
import com.tomyling.facturacion.modelo.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author new user
 */

@LocalBean
@Stateless
public class RolServicio extends RolDao
{
    public List<Rol> cargaRol()
    {
        List<Rol> lstRoles;
        lstRoles=this.cargaTodosRol();
        if( lstRoles==null || lstRoles.isEmpty())
        {
             return null;
        }
        else
        {
            return lstRoles; 
        }
        
    }
    
    public void creaRol(Rol rol) 
    {
         try
        {
            this.create(rol);
            FacesMessage msjsi=new FacesMessage();
            msjsi.setSeverity(FacesMessage.SEVERITY_INFO);
            msjsi.setSummary("Rol creado..");
            
            FacesContext.getCurrentInstance().addMessage("men", msjsi);
        }
        catch(Exception e) 
        {
            FacesMessage msjno=new FacesMessage();
            msjno.setSeverity(FacesMessage.SEVERITY_INFO);
            msjno.setSummary("Rol creado..");
            
            FacesContext.getCurrentInstance().addMessage("men", msjno);
            
        }     
        
        
    }
            
   // Eliminar Rol
    public void eliminaRol(Rol selectRol)
    {
        try
        {
            this.remove(selectRol);
        
            FacesMessage elimina=new FacesMessage();
            elimina.setSeverity(FacesMessage.SEVERITY_INFO);
            elimina.setSummary("Rol eliminado");
            
            FacesContext.getCurrentInstance().addMessage("msns", elimina);
        }    
        catch(Exception e)
        {
            FacesMessage noelimina=new FacesMessage();
            noelimina.setSeverity(FacesMessage.SEVERITY_INFO);
            noelimina.setSummary("NO se elimino el Rol..");
            
            FacesContext.getCurrentInstance().addMessage("msns1", noelimina);
            
        }

    }   
    
    public void editaRol(Rol selectRol)
    {
        this.edit(selectRol);
    }       
}
