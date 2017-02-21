/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Menu;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author new user
 */
@ManagedBean
@ViewScoped
public class PrincipalControlador extends Utilitarios implements Serializable{
    
    private InicioControlador inicio;
    private List<Menu> listaMenus;
    
    @PostConstruct
    private void init(){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.nuevo();
        this.inicio = (InicioControlador)session.getAttribute("inicioControlador");
        this.listaMenus=this.inicio.getListaMenu();
    
    }
    
    public void nuevo(){
    listaMenus=new ArrayList<>();
    }

    public InicioControlador getInicio() {
        return inicio;
    }

    public void setInicio(InicioControlador inicio) {
        this.inicio = inicio;
    }

    public List<Menu> getListaMenus() {
        return listaMenus;
    }

    public void setListaMenus(List<Menu> listaMenus) {
        this.listaMenus = listaMenus;
    }
    
}
