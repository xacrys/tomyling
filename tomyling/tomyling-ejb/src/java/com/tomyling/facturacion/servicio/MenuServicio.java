/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.MenuDao;
import com.tomyling.facturacion.modelo.Menu;
import com.tomyling.facturacion.modelo.RolMenu;
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
public class MenuServicio extends MenuDao {

    public List<Menu> listarMenus(List<RolMenu> rm) {
        List<Menu> listaMenus;
        listaMenus = this.listarMenusD(rm);
        if (listaMenus != null) {
            return listaMenus;
        } else {
            return null;
        }
    }
    
    public void guardaMenu(Menu menues) 
    {
        try
        {   
            this.create(menues);
            FacesMessage msjsi=new FacesMessage();
            msjsi.setSeverity(FacesMessage.SEVERITY_INFO);
            msjsi.setSummary("Menu creado con éxito");

            FacesContext.getCurrentInstance().addMessage("men", msjsi);
        }
        catch(Exception e)
       {
            FacesMessage msjno=new FacesMessage();
            msjno.setSeverity(FacesMessage.SEVERITY_INFO);
            msjno.setSummary("NO se guardo el Menu creado");

            FacesContext.getCurrentInstance().addMessage("men", msjno);
       }
   }
    
    public List<Menu> consultaMenu()
    {
        List<Menu> consulMenu;
        consulMenu=this.creaListaMenu();
        if(consulMenu==null || consulMenu.isEmpty())
        {
            return null;
        }
        else
        {
            return consulMenu;
        }
    }        
    
}
