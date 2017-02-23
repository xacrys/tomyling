/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;


import com.tomyling.facturacion.dto.MenuDinamicoDao;
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
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;

/**
 *
 * @author new user
 */
@ManagedBean
@ViewScoped
public class PrincipalControlador extends Utilitarios implements Serializable {

    private InicioControlador inicio;
    private List<Menu> listaMenus;
    private List<MenuDinamicoDao> listaMenusSubmenus;

    @PostConstruct
    private void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.nuevo();
        this.inicio = (InicioControlador) session.getAttribute("inicioControlador");
        this.listaMenus = this.inicio.getListaMenu();
        listaMenusSubmenus = iniciarMenu(listaMenus);

    }

    public void nuevo() {
        listaMenus = new ArrayList<>();
    }

    public List<MenuDinamicoDao> iniciarMenu(List<Menu> lista) {
        List<Menu> listasMenus = new ArrayList<>();
        List<DefaultMenuItem> listasSubMenus = new ArrayList<>();
       DefaultMenuModel model = new DefaultMenuModel();
        for (Menu m : lista) {
            if (m.getPadre() == 0) {
                listasMenus.add(m);
            } else {
                DefaultMenuItem item = new DefaultMenuItem(m.getNombreMenu());
                item.setUrl("http://www.primefaces.org");
                item.setIcon(m.getIcono());
                item.setId(m.getPadre().toString());
                listasSubMenus.add(item);
            }
        }
        List<MenuDinamicoDao> listafinal = new ArrayList<>();
        
        for (Menu mp : listasMenus) {

            MenuDinamicoDao mdd = new MenuDinamicoDao();
            mdd.setIdMenu(mp.getIdMenu());
            mdd.setNombreMenu(mp.getNombreMenu());
            mdd.setIcono(mp.getIcono());
            for (DefaultMenuItem dm : listasSubMenus) {
                if (mp.getIdMenu() == Integer.parseInt(dm.getId())) {
                    model.addElement(dm);
                }
            }
            mdd.setModelo(model);
            listafinal.add(mdd);
        }
        return listafinal;
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

    public List<MenuDinamicoDao> getListaMenusSubmenus() {
        return listaMenusSubmenus;
    }

    public void setListaMenusSubmenus(List<MenuDinamicoDao> listaMenusSubmenus) {
        this.listaMenusSubmenus = listaMenusSubmenus;
    }

}
