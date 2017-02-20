/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.RolMenu;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class RolMenuDao extends Generico<RolMenu> {

    public RolMenuDao() {
        super(RolMenu.class);
    }

    public List<RolMenu> obtenerRolPorMenu(Integer idRol) {
        List<RolMenu> listaRolMenu;
        Query query;
        String jpql = "SELECT r FROM RolMenu r WHERE r.rolMenuPK.idRol = :idRol";
        query=getEntityManager().createQuery(jpql).setParameter("idRol", idRol);
        listaRolMenu=query.getResultList();
        if(listaRolMenu==null || listaRolMenu.isEmpty()){
            return null;
        }
        else{
            return listaRolMenu;
        }
        
    }
}
