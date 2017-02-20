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
}
