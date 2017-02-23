/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dto;

import com.tomyling.facturacion.modelo.Menu;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author new user
 */
public class MenuDinamicoDao implements Serializable{
    
    
    private Integer idMenu;
    private String nombreMenu;
    private String icono;
    private List<Menu> listaSubmenu;

    public MenuDinamicoDao(Integer idMenu, String nombreMenu, String icono, List<Menu> listaSubmenu) {
        this.idMenu = idMenu;
        this.nombreMenu = nombreMenu;
        this.icono = icono;
        this.listaSubmenu = listaSubmenu;
    }

   

    public MenuDinamicoDao() {
    }
    
    
    

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public List<Menu> getListaSubmenu() {
        return listaSubmenu;
    }

    public void setListaSubmenu(List<Menu> listaSubmenu) {
        this.listaSubmenu = listaSubmenu;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    
    
    
}
