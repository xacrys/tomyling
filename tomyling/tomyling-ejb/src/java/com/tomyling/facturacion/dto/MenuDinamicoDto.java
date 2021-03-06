/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dto;

import java.io.Serializable;
import java.util.List;
import org.primefaces.model.menu.DefaultMenuModel;

/**
 *
 * @author new user
 */
public class MenuDinamicoDto implements Serializable {

    private Integer idMenu;
    private String nombreMenu;
    private String icono;
    private DefaultMenuModel modelo;
    private String url;

    public MenuDinamicoDto() {
    }

    public MenuDinamicoDto(Integer idMenu, String nombreMenu, String icono, DefaultMenuModel modelo, String url) {
        this.idMenu = idMenu;
        this.nombreMenu = nombreMenu;
        this.icono = icono;
        this.modelo = modelo;
        this.url = url;
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public DefaultMenuModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultMenuModel modelo) {
        this.modelo = modelo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
