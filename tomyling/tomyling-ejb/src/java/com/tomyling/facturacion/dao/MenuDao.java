/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Menu;
import com.tomyling.facturacion.modelo.RolMenu;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class MenuDao extends Generico<Menu> {

    public MenuDao() {
        super(Menu.class);
    }

    public List<Menu> listarMenusD(List<RolMenu> rm) {
        
        List<Menu> listaMenu;
        List<Integer> listaIdMenus=new ArrayList<>();
        Query query;
        String jpql = ("SELECT m FROM Menu m WHERE m.idMenu IN :idMenu");
        for(RolMenu r:rm)
        {
        listaIdMenus.add(r.getRolMenuPK().getIdMenu());
        }
        query=getEntityManager().createQuery(jpql).setParameter("idMenu", listaIdMenus);
        listaMenu=query.getResultList();
        if(listaMenu==null || listaMenu.isEmpty())
        {
            return null;
        }
        else{
            return listaMenu;
        }
                
    }
}
