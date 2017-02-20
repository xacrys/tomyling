/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Menu;
import com.tomyling.facturacion.modelo.RolMenu;
import com.tomyling.facturacion.modelo.UsuRol;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import com.tomyling.facturacion.modelo.Usuario;
import com.tomyling.facturacion.servicio.MenuServicio;
import com.tomyling.facturacion.servicio.RolMenuServicio;
import com.tomyling.facturacion.servicio.UsuRolServicio;
import com.tomyling.facturacion.servicio.UsuarioServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author new user
 */
@ManagedBean
@ViewScoped
public class InicioControlador extends Utilitarios implements Serializable {

    private String nombre;
    private String clave;

    @EJB
    private UsuarioServicio usuarioServicio;

    @EJB
    private UsuRolServicio usuRolServicio;

    @EJB
    private RolMenuServicio rolMenuServicio;

    @EJB
    private MenuServicio menuServicio;

    @PostConstruct
    private void init() {
        this.nombre = "";
        this.clave = "";
    }

    public void validarUsuario() throws IOException {
        Usuario u = usuarioServicio.existeUsuario(nombre, clave);
        if (u == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario no existe.!", "El usuario no existe."));
        } else {
            UsuRol ur = usuRolServicio.obtenerUsuRol(u.getIdUsuario());
            if (ur != null) {
                List<RolMenu> rm = rolMenuServicio.obtenerRolMenu(ur.getUsuRolPK().getIdRol());
                if (rm != null || rm.isEmpty()) {
                    List<Menu> listaMenu = new ArrayList<>();
                    listaMenu = menuServicio.listarMenus(rm);
                     redirect("/pages/inicio/inicio.edw");
                }
            }
           
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
